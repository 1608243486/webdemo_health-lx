
<%--
  Created by IntelliJ IDEA.
  User: wuyuanzhou
  Date: 2023/4/26
  Time: 19:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@1/css/pico.min.css" />
    <title>LOGIN pages</title>
    <style>
        /* Grid */
        body>main {
            display: flex;
            flex-direction: column;
            justify-content: center;
            min-height: calc(100vh - 7rem);
            padding: 1rem 0;
        }

        article {
            padding: 0;
            overflow: hidden;
        }

        article div {
            padding: 1rem;
        }

        @media (min-width: 576px) {
            body>main {
                padding: 1.25rem 0;
            }

            article div {
                padding: 1.25rem;
            }
        }

        @media (min-width: 768px) {
            body>main {
                padding: 1.5rem 0;
            }

            article div {
                padding: 1.5rem;
            }
        }

        @media (min-width: 992px) {
            body>main {
                padding: 1.75rem 0;
            }

            article div {
                padding: 1.75rem;
            }
        }

        @media (min-width: 1200px) {
            body>main {
                padding: 2rem 0;
            }

            article div {
                padding: 2rem;
            }
        }

        /* Nav */
        summary[role="link"].secondary:is([aria-current], :hover, :active, :focus) {
            background-color: transparent;
            color: var(--secondary-hover);
        }

        /* Hero Image */
        article div:nth-of-type(2) {
            display: none;
            background-color: #374956;
            background-image: url("image/copyimage.jpg");
            background-position: center;
            background-size: cover;
        }

        @media (min-width: 992px) {
            .grid>div:nth-of-type(2) {
                display: block;
            }
        }

        /* Footer */
        body>footer {
            padding: 1rem 0;
        }
    </style>
</head>
<body>
<%
    String rememberUserName="";
    String rememberPassword="";

    System.out.println("jsp isRemember"+session.getAttribute("isRemember"));
    if (session.getAttribute("isRemember")!=null&&session.getAttribute("isRemember").equals(true)){
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for (Cookie cookie:cookies) {//查找cookie中记住的用户名和密码
                if("rememberUser".equals(cookie.getName())){
                    rememberUserName = cookie.getValue().split("&")[0];
                    rememberPassword = cookie.getValue().split("&")[1];
                }
            }
        }
    }




%>
<!-- Main -->
<main class="container">
    <article class="grid">
        <div>
            <hgroup>
                <h1>Sign in</h1>
                <h2>Welcome to the HEALTH website</h2>
            </hgroup>
            <form action="LoginServlet" method="post" id="loginForm" >
                <input
                        type="text"
                        name="username"
                        value="<%=rememberUserName%>"
                        placeholder="Username"
                        aria-label="username"
                        autocomplete="nickname"
                        required pattern="[\u4e00-\u9fa5 A-Z a-z 0-9 _]{3,20}"
                />
                <input
                        type="password"
                        name="password"
                        value="<%=rememberPassword%>"
                        placeholder="Password"
                        aria-label="Password"
                        autocomplete="current-password"
                        required pattern="\w{8,20}"
                />
                <fieldset>
<%--                    <div style="display:-moz-inline-block;float: left; margin-inline-end: 20px">--%>
                        <label for="remember">
                            <input type="checkbox" style="display: -moz-inline-block; float: left" value="true" role="switch" id="remember" name="remember" />
                            Remember me
                        </label>
<%--                    </div>--%>
<%--                    <div style="display: inline-block ;float: left; ">--%>
<%--                        <input type="checkbox" style="display:-moz-inline-block; float: left" value="true" role="switch" id="autologin" name="autologin">--%>
<%--                          Autologin--%>
<%--                    </div>--%>

                </fieldset>

                <button type="submit" class="contrast" >Login</button>
            </form>
            <a href="register.jsp" style="width: 30%" role="button" class="outline">点击注册</a>
        </div>
        <div>
        </div>
    </article>

</main>
<!-- ./ Main -->
</body>
</html>
