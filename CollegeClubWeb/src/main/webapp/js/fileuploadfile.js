 $('#file-fr').fileinput({
        language: 'zh',
        uploadAsync: true,
        uploadUrl: '/pic/imgadd',
        allowedFileExtensions: ['zip','rar','7z','gz','tar','tar2'],
        maxFileSize: 20480,
    }).on("fileuploaded", function(event,data) { //异步上传成功结果处理
    	 $("#caFile").val("");
         $("#caFile").val(data.response);
     })