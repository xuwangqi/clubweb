 $('#file-fr').fileinput({
        language: 'zh',
        uploadAsync: true,
        uploadUrl: '/pic/imgadd',
        allowedFileExtensions: ['jpg', 'png', 'gif'],
        maxFileSize: 4096,
    }).on("fileuploaded", function(event,data) { //异步上传成功结果处理
    	 $("#userImage").val("");
         $("#userImage").val(data.response);
     })