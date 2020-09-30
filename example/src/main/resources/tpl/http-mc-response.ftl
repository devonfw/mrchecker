<#ftl output_format="HTML">
<#-- @ftlvariable name="data" type="io.qameta.allure.attachment.http.HttpResponseAttachment" -->
<div>HTTP Status Code: <#if data.responseCode??>${data.responseCode} <#else>Unknown</#if></div>
<#if data.url??>
    <div>${data.url}</div></#if>

<#if (data.headers)?has_content>
    <h4>HEADERS</h4>
    <div>
        <#list data.headers as name, value>
            <div>${name}: ${value}</div>
        </#list>
    </div>
</#if>

<#if data.body??>
    <h4>BODY</h4>
    <div>
    <pre class="preformated-text">
    ${data.body}
    </pre>
    </div>
</#if>

<#if (data.cookies)?has_content>
    <h4>COOKIES</h4>
    <div>
        <#list data.cookies as name, value>
            <div>${name}: ${value}</div>
        </#list>
    </div>
</#if>