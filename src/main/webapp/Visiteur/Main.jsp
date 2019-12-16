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

    </head>
    <body> 
<nav class="site-header sticky-top py-1">
  <div class="container d-flex flex-column flex-md-row justify-content-between">

    <a class="py-2 d-none d-md-inline-block" href="#"></a>
    <a class="redirect py-2 d-none d-md-inline-block" value="connexion" href="<c:url value="Connexion.jsp" />">Connexion</a>
    <a class="redirect py-2 d-none d-md-inline-block" value="inscription" href="<c:url value="Inscription.jsp" />" >Inscription</a>
    <a class="py-2 d-none d-md-inline-block" href="#"></a>


  </div>
</nav>    
            <img src ="../Images/LogoEcoPlusPlus.png" alt="ECO PLUS PLUS" class="logo">
    	<div>
            <form>
                  <p>
                     <label class="sign" >Selectionné une catégorie</label>
                     <select id = "listeCategorie">
                         <option name name="catego" value="Tous">Tous</option>
                         <c:forEach var="cat" items="${Categorie}">
                             <option name name="catego" value="${cat}">${cat}</option>
                         </c:forEach>
                     </select>
                  </p>
            </form>
	</div>
                
                
<div>           
    <ul>
        <c:forEach var="objet" items="${Produit}">
            <li class="liste">
                ${objet.getName()}
                ${objet.getPrix()}
            </li>
        </c:forEach>
    </ul>
</div>
                
    </body>
</html>
