<!--убираем вкладку User list для неавторизированных пользователей-->
<!--для определения переменных внутри шаблона (?? - приведение к boolean).
Если этот объект определен в контексте => можем работать с сессией-->
<#assign
    known = Session.SPRING_SECURITY_CONTEXT??
>

<#if known> <!-- если сессия сущ-->
<!--значение будет содержать все поля и методы нашего user из User класса-->
    <#assign
        user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        name = user.getUsername()
        isAdmin = user.isAdmin()
        currentUserId = user.getId()
    >
<#else>
    <#assign
        name = "unknown"
        isAdmin = false
        currentUserId = -1
    >
</#if>