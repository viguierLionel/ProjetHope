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
        <link rel="stylesheet" type="text/css" href="inscription.css">
        <link rel="stylesheet" type="text/css" href="../bootstrap-4.4.1/css/bootstrap-grid.css">        
        <title>Inscription</title>
    </head>
    <body>
        <img src ="../Images/LogoEcoPlusPlus.png" alt="ECO PLUS PLUS" class="logo">
        <div class="main">
            <form class="form1">

                <input class="input" type="text" name="contact" placeholder="Contact">

                <input class="input" type="password" name="motDePasse" placeholder="Mot de passe">
                
                <input class="input" type="text" name="Societe" placeholder="Société">
                
                <input class="input" type="text" name="Fonction" placeholder="Fonction">
                
                <input class="input" type="text" name="Adresse" placeholder="Adresse">
                
                <input class="input" type="text" name="Ville" placeholder="Ville">
                
                <input class="input" type="text" name="Region" placeholder="Regiom">
                
                <input class="input" type="number" name="Code_postal" placeholder="Code postal">
                
                <input class="input" type="text" name="Pays" placeholder="Pays">
                
                <input class="input" type="tel" name="Telephone" placeholder="Numéro de tel.">
                
                <input class="input" type="number" name="Fax" placeholder="Fax">
                
                <a class="submit" value="connecter" href="<c:url value="Main.jsp" />" >S'inscrire</a>
            </form>
            <br>
            <a class="connexion" value="retour" href="<c:url value="Connexion.jsp" />" >Connexion</a> <a class="retour" value="retour" href="<c:url value="Main.jsp" />" >Retour</a>
        </div>
    </body>
</html>
