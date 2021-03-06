<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<a href="<c:url value="test.htm"/>" >test</a> --%> <%-- Fix --%>

<!-- Sidebar -->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="minside.htm">SKS</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse navbar-ex1-collapse">

        <ul class="nav navbar-nav navbar-right navbar-user">
            <li class="dropdown user-dropdown">
                <a href="" class="dropdown-toggle" data-toggle="dropdown">${sessionScope.innloggetBruker.fornavn}
                    <b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href="minside.htm">Minside</a></li>
                    <li><a href="endrePassord.htm"><i class="fa fa-power-off"></i>Endre Passord</a></li>
                    <li><a href="loggUt.htm"><i class="fa fa-power-off"></i> Logg ut</a></li>
                    <li class="divider"></li>
                </ul>
            </li>
        </ul>
        <ul class="nav navbar-nav side-nav">
            <li><a href="login.htm">Logg inn</a></li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-caret-square-o-down"></i>
                    Emner <b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <c:forEach items="${sessionScope.innloggetBruker.emne}" var="emne">
                        <li><a onclick="emnekodeFraMenu(this.id)" id = "${emne.emneKode}">${emne.emneNavn}</a></li>
                    </c:forEach>
                </ul>
            </li>

            <li><a href="koOversikt.htm">K&oslash; oversikt</a></li>

            <li><a href="adminBrukere.htm">Administrer brukere</a></li>
            <li><a href="ovingsOpplegg.htm">&Oslash;vingsopplegget</a></li>
            <li><a href="opprettEmne.htm" >Opprett emne</a></li>
            <li><a href="adminFag.htm">Administrer fag</a></li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-caret-square-o-down"></i>
                    Administrer studenter <b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href="nyStudent.htm">Ny student</a></li>
                    <li><a href="endreStudent.htm">Endre student</a></li>
                </ul>
            </li>
            <li><a href="godkjenningsoversikt.htm">Godkjenningsoversikt</a></li>

        </ul>
    </div>
    <!-- /.navbar-collapse -->
</nav>

<script src="<c:url value="/resources/js/menyScript.js"/>"></script>