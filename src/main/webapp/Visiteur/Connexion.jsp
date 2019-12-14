<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="connexion.css">
        <link rel="stylesheet" type="text/css" href="../bootstrap-4.4.1/css/bootstrap-grid.css">       
        <title>Connexion</title>
    </head>
    
    <body>
        <img src ="../Images/LogoEcoPlusPlus.png" alt="ECO PLUS PLUS" class="logo">
        
        <div class="main">
            <p class="sign" align="center">Connectez-vous</p>
                <form class="form1">
                    <input class="input" name="contact" type="text" align="center" placeholder="Nom d'utilisateur" required>
                    <input class="input" name="motDePasse" type="password" align="center" placeholder="Mot de passe" required>
                    <a type="submit" class="submit" align="center" value="connecter" href="<c:url value="Main.jsp"/>" >Connexion</a>
                </form>
            <br><br>
                <div> <a class="inscription" value="inscription" href="<c:url value="Inscription.jsp" />" >Inscription</a> <a class="retour" value="retour" href="<c:url value="Main.jsp"/>" >Retour</a>  </div>
        </div>
    </body>
</html>