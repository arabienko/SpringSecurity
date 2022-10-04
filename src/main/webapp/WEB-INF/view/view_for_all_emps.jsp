<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <title>Spring+Hibernate+MVC+AOP</title>
</head>
<body class="container-fluid p-10 ">
<h1>
Info workers ...
</h1>
<br>
<br>
<security:authorize access = "hasRole('HR')">
<input type = "button" value="Salary"
onclick ="window.location.href = 'hr_info'"/>
only for HR staff...
</security:authorize>

<br>
<br>
<security:authorize access = "hasRole('MANAGER')">
<input type = "button" value="Performance"
onclick ="window.location.href = 'customer_info'"/>
only for customer...
</security:authorize>
</body>
</html>