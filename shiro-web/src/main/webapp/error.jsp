<%@page language="java" contentType="text/html;charset=UTF-8" session="true" isErrorPage="true"%>
<%
    if (exception == null) {
        exception = (Throwable)request.getAttribute("java.lang.Exception");
    }
%>
 
<h3>错误信息</h3>
 
 <pre>
<%
     if (exception != null) {
         out.print(exception.getMessage());
//        exception.printStackTrace(response.getWriter());
     }

%>
</pre>
