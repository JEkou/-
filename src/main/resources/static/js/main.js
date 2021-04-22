var a = "";
$(function () {
    while (a !== "admin") {
        a = prompt("请输入密码");
    }
    networkStatus();
    $("#myTab a[href='#mySystem']").tab("show");
})

function dataCount() {
    $.post("/getMessageCount", function (data) {
        console.log(data);
        $("#message-count").html(20);
    })
}

function getConfig() {
    $.get("/getConfig", function (data) {
        console.log(data);
        $("#local-port").attr("value", data.localPort);
        $("#cloud-service-address").attr("value", data.cloudAddress);
        $("#cloud-service-port").attr("value", data.cloudPort);
        $("#frequency").attr("value", data.frequency);
        $("#my-key").attr("value", data.myKey);
    })
}

function getLog() {
    $.get("/log", function (data) {
        $("#log-message").html(data);
    })
}

function systemInfo() {
    $.get("/operationSystemInfo", function (data) {
        $("#operation-system").html(data);
    });
    $.get("/systemVersionNumber", function (d) {
        $("#system-version").html(d);
    });
}

function saveConfig() {
    $.ajax({
        type: "post",
        url: "/saveConfig",
        data: $("#config-form").serialize(),
        dataType: "json",
        success: function (data) {
            console.log(data)
            if (data == 1) {
                alert("修改成功!");
            } else {
                alert("保存失败!");
            }
        }
    });
}

function networkStatus(){
    $("#network-status").html("正在查询...");
    $("#system-service").html("正在验证...");
    $.ajax({
        url:"/isConnect",
        type: "get",
        timeout: "5000",
        success:function(data){
            let status = "不通畅";
            $("#system-service").html("正在运行");
            if(data == true){
                status="通畅";
            }
            $("#network-status").html(status);
        },
        complete:function(XMLHttpRequest,status){
            if(status=='timeout'){
                setFailedMessage();
            }
        }
    })
}
function setFailedMessage(){
    $("#network-status").html("不通畅");
    $("#system-service").html("已停止");
}
