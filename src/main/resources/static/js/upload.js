$(function () {
    $("#upload").click(function () {
        var formData = new FormData();
        formData.append("fileToUpload", document.getElementById("file1").files[0]);
        $.ajax({
            url: "/image/upload",
            type: "POST",
            data: formData,
            /**
             *必须false才会自动加上正确的Content-Type
             */
            contentType: false,
            /**
             * 必须false才会避开jQuery对 formdata 的默认处理
             * XMLHttpRequest会对 formdata 进行正确的处理
             */
            processData: false,
            success: function (data) {
                alert(data.message);
                $('#fileName').val(data.file);
            },
            error: function () {
                alert("上传失败！");
            }
        });
    });
});