package com.example.task;

import com.example.entity.Asset;
import com.example.entity.Localize;
import com.example.entity.Report;
import com.example.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * @author USER
 */

@Component
@EnableScheduling
public class ReceiveSchedule implements SchedulingConfigurer {

    private int timer = 500;
    private int inetSocketAddress = 888;
    private int timeOut = 3000;

    Logger logger = LoggerFactory.getLogger(getClass());

    private ReportService reportService;


    @Autowired
    public void setReportService(ReportService reportService) {
        this.reportService = reportService;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.addTriggerTask(() -> {

            Report report = receiveReport();
            if(report != null) {
                System.out.println("----shoudao----");
                //logger.debug("接收到");
                reportService.saveReport(report);
            }

        }, triggerContext -> {
            //设置任务下次开始的时间间隔(ms)
            PeriodicTrigger periodicTrigger = new PeriodicTrigger(timer);
            return periodicTrigger.nextExecutionTime(triggerContext);
        });
    }


    private Report receiveReport(){
        Report report = null;
        DatagramChannel channel = null;
        try {
            channel = DatagramChannel.open();
            channel.configureBlocking(true);
            channel.socket().bind(new InetSocketAddress(inetSocketAddress));
            channel.socket().setSoTimeout(timeOut);

            ByteBuffer buffer = ByteBuffer.allocate(2048);
            List<Localize> localizeList = new ArrayList<>();
            List<Asset> assetList = new ArrayList<>();

            channel.receive(buffer);
            buffer.flip();
            int head1 = buffer.get() & 0xFF;
            int head2 = buffer.get() & 0xFF;

            if (head1 == 0xFA) {
                byte ip1 = buffer.get(),ip2 = buffer.get(),ip3 = buffer.get(),ip4 = buffer.get();
                int length = buffer.getChar();

                System.out.println(buffer);

                if (buffer.limit() - buffer.position() == length) {
                    while (buffer.position() < buffer.limit()) {
                        int localizeId = buffer.getChar();
                        int localizeStatus = buffer.get() & 0xFF;
                        int localizeLength = buffer.get() & 0xFF;

                        if (localizeStatus == 0x00) {
                            int repeat = localizeLength / 17;
                            int mode = localizeLength % 17;

                            if (mode == 0 && repeat > 0) {
                                for (int i = 0; i < repeat; i++) {
                                    int id = buffer.getChar();
                                    int rssI = buffer.get() & 0xFF;
                                    int status = buffer.getChar() & 0xFFFF;
                                    int volt = buffer.getShort() & 0xFFFF;
                                    int amp = buffer.getShort() & 0xFFFF;
                                    int currentPower = buffer.getInt();
                                    int ratePower = buffer.getInt();

                                    Asset asset = new Asset(id + "",rssI + "",status + "",volt + "",amp + "",currentPower + "",ratePower + "");
                                    assetList.add(asset);
                                }
                            }
                        }
                        Localize localize = new Localize(localizeId + "",localizeStatus,localizeLength,assetList);
                        for (Asset asset : assetList) {
                            asset.setLocalize(localize);
                        }
                        localizeList.add(localize);
                    }
                }
                report = new Report();
                report.setHead(head1 + "" + head2);
                report.setIp(Byte.toUnsignedInt(ip1) + "." + Byte.toUnsignedInt(ip2) + "." + Byte.toUnsignedInt(ip3) + "." + Byte.toUnsignedInt(ip4));
                report.setLength(length);
                report.setLocalizeList(localizeList);
                for (Localize localize : localizeList) {
                    localize.setReport(report);
                }
            }
        } catch (Exception e) {
        } finally {
            try {
                channel.disconnect();
                channel.close();
            } catch (IOException e) {
            }
        }
        return report;
    }
}
