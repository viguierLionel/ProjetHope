<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="mainadmin.css">
        <link rel="stylesheet" type="text/css" href="../bootstrap-4.4.1/css/bootstrap-grid.css">
        <title>EcoPlusPlus</title>

    </head>
    <body> 
<nav class="site-header sticky-top py-1">
  <div class="container d-flex flex-column flex-md-row justify-content-between">

    <a class="py-2 d-none d-md-inline-block" href="#"></a>
    <a class="redirect py-2 d-none d-md-inline-block" value="connexion" href="<c:url value="Editiondonnee.jsp" />">Modifier données</a>
    <a class="redirect py-2 d-none d-md-inline-block" value="connexion" href="<c:url value="../Visiteur/Main.jsp" />">Deconnexion</a>
    <a class="py-2 d-none d-md-inline-block" href="#"></a>


  </div>
  
    
    
</nav>    
            <img src ="../Images/LogoEcoPlusPlus.png" alt="ECO PLUS PLUS" class="logo">
                
        <!-- Ajout d'objet -->        
        <div class="ajoutObjet">
            <form>
                <input type="number" name="ref" placeholder="Numéro de référence">
                <input type="text" name="nom" placeholder="Nom"> 
                <input type="text" name="fournisseur" placeholder="Fournisseur">
                <input type="text" name="cat" placeholder="Catégorie">
                <input type="number" name="prixUnitaire" placeholder="Prix Unitaire">
                <input types="number" name="stock" placeholder="Unités en Stock">
                <input types="number" name="reaprovision" placeholder="Réaprovision">
                    
                <select name="disponibilite">
                    <option value="0">Disponible</option>
                    <option value="1">Indisponible</option>
                </select>
                    <br>
                    <br> 
                <textarea rows="4" cols="100"></textarea>
                <input type="submit" value="Ajouter ">
            </form>
        </div>
                
        <!-- Categorie -->         
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
                
<!-- Liste d'objet -->                 
<div >           
    <ul class="tousLesObjets">
            <li class="liste">
            <form>
                <p class="objet">${objet.getnProduit()}</p>
                
                <input type="number" name="ref" placeholder="Numéro de référence" value="${objet.getReference}">
                <input type="text" name="nom" placeholder="Nom" value="${objet.getnProduit}"> 
                <input type="text" name="fournisseur" placeholder="Fournisseur" value="${objet.getFournisseur}">
                <input type="text" name="cat" placeholder="Catégorie" value="${objet.getCategorie}">
                <input type="number" name="prixUnitaire" placeholder="Prix Unitaire" value="${objet.getPrixUnitaire}">
                <input types="number" name="stock" placeholder="Unités en Stock" value="${objet.getUnitesEnStock}">
                <input types="number" name="reaprovision" placeholder="Réaprovision" value="${objet.NiveauReaprovi}"> 
                <select name="disponibilite">
                    {% if ${objet.isIndisponible()} %} 
                        <option value="0">Disponible</option>
                        <option value="1">Indisponible</option>
                    {% else %}
                        <option value="1">Indisponible</option>
                        <option value="0">Disponible</option>
                    {% endif %}
                </select>
                    <br>
                    <br> 
                <input type="submit" value="Ajouter ">
            </form>
            </li>
                
                
               
        <c:forEach var="objet" items="${Produit}">
            <li class="liste">
            <form>
                <p class="objet">${objet.getnProduit()}</p>
                
                <input type="number" name="ref" placeholder="Numéro de référence" value="${objet.getReference()}">
                <input type="text" name="nom" placeholder="Nom" value="${objet.getnProduit()}"> 
                <input type="text" name="fournisseur" placeholder="Fournisseur" value="${objet.getFournisseur()}">
                <input type="text" name="cat" placeholder="Catégorie" value="${objet.getCategorie()}">
                <input type="number" name="prixUnitaire" placeholder="Prix Unitaire" value="${objet.getPrixUnitaire()}">
                <input types="number" name="stock" placeholder="Unités en Stock" value="${objet.getUnitesEnStock()}">
                <input types="number" name="reaprovision" placeholder="Réaprovision" value="${objet.NiveauReaprovi()}">
                    
                <select name="disponibilite">
                    {% if ${objet.isIndisponible()} %} 
                        <option value="0">Disponible</option>
                        <option value="1">Indisponible</option>
                    {% else %}
                        <option value="1">Indisponible</option>
                        <option value="0">Disponible</option>
                    {% endif %}
                </select>
                    <br>
                    <br> 
                <input type="submit" value="Ajouter ">
            </form>
            </li>
        </c:forEach>
    </ul>
</div>
                
    </body>
</html>

