<%-- 
    Document   : Inscription
    Created on : 10 dec. 2019, 14:59:31
    Author     : Gebruiker
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="editiondonnee.css">
        <link rel="stylesheet" type="text/css" href="../bootstrap-4.4.1/css/bootstrap-grid.css">        
        <title>Edition de données</title>
    </head>
    <body>
        <img src ="../Images/LogoEcoPlusPlus.png" alt="ECO PLUS PLUS" class="logo">
        <div class="main">
            <form class="form1">

                <input class="input" type="text" name="contact" value="${client.getContact()}" placeholder="Contact">

                <input class="input" type="password" name="motDePasse" value="${client.getCode()}" placeholder="Mot de passe">
                
                <input class="input" type="text" name="Societe" value="${client.getSociete()}" placeholder="Société">
                
                <input class="input" type="text" name="Fonction" value="${client.getFonction()}" placeholder="Fonction">
                
                <input class="input" type="text" name="Adresse" value="${client.getAdresse()}" placeholder="Adresse">
                
                <input class="input" type="text" name="Ville" value="${client.getVille()}" placeholder="Ville">
                
                <input class="input" type="text" name="Region" value="${client.getRegion()}" placeholder="Region">
                
                <input class="input" type="number" name="Code_postal" value="${client.getCodePostal()}" placeholder="Code postal">
                
                <input class="input" type="text" name="Pays" value="${client.getPays()}" placeholder="Pays">
                
                <input class="input" type="tel" name="Telephone"value="${client.getTelephone()}"  placeholder="Numéro de tel.">
                
                <input class="input" type="tel" name="Fax" value="${client.getFax()}" placeholder="Fax">
                
                <a class="save" value="connecter" href="<c:url value="Editiondonnee.jsp" />" >Sauvegarder</a>
            </form>
            <br>
            <a class="retour" value="retour" href="<c:url value="Maincl.jsp" />" >Retour</a>
        </div>
    </body>
</html>

