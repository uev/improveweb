<?xml version="1.0"?>
<xsl:stylesheet
        xmlns="http://www.w3.org/1999/xhtml"
        xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
        xmlns:xi="http://www.w3.org/2001/XInclude"
        xmlns:h="http://java.sun.com/jsf/html"
        xmlns:p="http://primefaces.org/ui" xmlns:f="http://java.sun.com/jsf/core" version="1.0">
    <xsl:template match="/">
        <p:panel id="filter" header="{/filter/@headerOfPanel}">
        <h:form id="{/filter/@idOfForm}">
        <xsl:for-each select="//filter/form">
            <div>
                <p:outputLabel for="{@model}" value="{@label}" style="display: block"/>
                <p:inputText id="{@model}" value="{@target}">
                    <f:validateLength minimum="1" />
                </p:inputText>
            </div>
        </xsl:for-each>
            <xsl:if test="/filter/button/@process and  /filter/button/@update and /filter/button/@actionListener">
                <div id="div-submit">
                    <p:commandButton id="submit" value="{/filter/button/@label}" process="{/filter/button/@process}" actionListener="{/filter/button/@actionListener}" update="{/filter/button/@update}" />
                </div>
                <p:messages id="errors" style="display: block"/>
            </xsl:if>
        </h:form>
        </p:panel>
    </xsl:template>
</xsl:stylesheet>