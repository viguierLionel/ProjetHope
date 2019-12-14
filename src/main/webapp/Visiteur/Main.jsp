<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="main.css">
        <link rel="stylesheet" type="text/css" href="../bootstrap-4.4.1/css/bootstrap-grid.css">
        <title>EcoPlusPlus</title>
        <style>

        </style>
    </head>
    <body> 
        <div class="container-fluid">
            <div class="row">
                <div class="span1">
                    <form method='GET'>
                        <input type="hidden" name="action" value="CONNEXION" >
                        <a class="redirect" value="retour" href="<c:url value="Connexion.jsp" />" >Connexion</a>
                    </form>
                </div>

                <div class="span1">
                    <form method='GET'>
                        <input type="hidden" name="action" value="INSCRIPTION" >
                        <a class="redirect" value="inscription" href="<c:url value="Inscription.jsp" />" >Inscription</a>
                    </form>
                </div>
            </div>
        </div>
        
            <img src ="../Images/LogoEcoPlusPlus.png" alt="ECO PLUS PLUS" class="logo">
    	<div>
            <form>
                  <p>
                     <label class="sign" >Selectionné une catégorie</label>
                     <select id = "listeCategorie">
                         <option name name="catego" value="Tous">Tous</option>
                         <c:forEach var="cat" items="${Categorie}">
                             <option name name="catego" value="${Categorie}">${Categorie}</option>
                         </c:forEach>
                     </select>
                  </p>
            </form>
	</div>
    </body>
</html>
