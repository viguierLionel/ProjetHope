<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="Main.css">
        <link rel="stylesheet" type="text/css" href="../bootstrap-4.4.1/css/bootstrap-grid.css">
        <title>EcoPlusPlus</title>
        <style>

        </style>
    </head>
    <body> 
        <div class="container-fluid">
            <div class="row">
                <div class="span1 offset-10">
                    <form method='GET'>
                        <input type="hidden" name="action" value="CONNEXION" >
                        <input type="submit" value="Connexion">
                    </form>
                </div>

                <div class="span1">
                    <form method='GET'>
                        <input type="hidden" name="action" value="INSCRIPTION" >
                        <input type="submit" value="inscription">
                    </form>
                </div>
            </div>
        </div>
        
            <img src ="../Images/LogoEcoPlusPlus.png" alt="ECO PLUS PLUS" class="logo">
    	<div>
            <form>
                  <p>
                     <label>Select list</label>
                     <select id = "listeCategorie">
                         <c:forEach var="cat" items="${Categorie}">
                             <option name name="catego" value="${Categorie}">${Categorie}</option>
                         </c:forEach>
                     </select>
                  </p>
            </form>
	</div>
    </body>
</html>
