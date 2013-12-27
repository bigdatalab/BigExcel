<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" version="2.0">
    <jsp:directive.page language="java"
        contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
    <jsp:text>
        <![CDATA[ <?xml version="1.0" encoding="UTF-8" ?> ]]>
    </jsp:text>
    <jsp:text>
        <![CDATA[ <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> ]]>
    </jsp:text>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Login</title>

<style type="text/css">
            .column1{width: 10px;height:auto}
            .column2{width: 5px;height:auto}
            </style>
           
</head>
<body style="background-color: #E5E5E5">
<f:view>
		<f:loadBundle basename="org.bsanalytics.login.login" var="login"/>
		<h:messages></h:messages>

		<h:panelGroup style="height: 120px; text-align:center">	
			<h:panelGrid
				style="height: 350px; width: 450px;  margin: 120px auto; background-color: #7F7F7F">
				
				<h:graphicImage url="/resources/images/pie_chart.bmp"></h:graphicImage><h:panelGrid columns="2"
					style="height: 102px; width: 311px; margin: 40px auto;  text-align:center">
					
					<h:outputText value="#{login.username}"
						style="color: #FFFFFF; font-variant: small-caps;"></h:outputText>

					<h:inputText value="#{loginBean.username}"></h:inputText>
					<h:outputText value="#{login.password}"
						style="color: #FFFFFF; text-transform: capitalize; font-variant: small-caps"></h:outputText>
					<h:inputSecret value="#{loginBean.password}"></h:inputSecret>
					<h:form>
					</h:form>
					<h:form>
					</h:form>
					<h:form>
						
					</h:form>
					<h:form>
					<h:commandButton value="Login" action="login_in" style="width: 79px; "></h:commandButton>
					</h:form>
				</h:panelGrid>
			</h:panelGrid>			
		</h:panelGroup>
	</f:view>
</body>
</html>
</jsp:root>