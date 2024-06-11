<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Test Page</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $.get("test", function(data) {
            $("#result").html(data);
        });
    });
</script>
</head>
<body>
    <h1>Test Result</h1>
    <div id="result">
        <%= request.getAttribute("result") %>
    </div>
</body>
</html>
