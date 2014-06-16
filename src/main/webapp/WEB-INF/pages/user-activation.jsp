<%@include file="includes/header.jsp"%>
${message}
<form action="/registration/confirm/${uid}" method="post">
<button type="submit">Send token</button>
</form>
<%@include file="includes/footer.jsp"%>