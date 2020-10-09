<#ftl output_format="HTML">
<#-- @ftlvariable name="data" type="io.qameta.allure.attachement.http.HttpRequestAttachment" -->
<div><#if data.method??>${data.method}<#else>GET</#if> <#if data.url??>${data.url}<#else>Unknown</#if></div>

<#if (data.headers)?has_content>
    <h4>HEADERS</h4>
    <div>
        <#list data.headers as name, value>
            <div>${name}: ${value}</div>
        </#list>
    </div>
</#if>
<#if (data.formParams)?has_content>
    <h4>FORM PARAMETERS</h4>
    <div>
        <#list data.formParams as name, value>
            <div>${name}: ${value}</div>
        </#list>
    </div>
</#if>

<#if (data.multiPartFormData)?has_content>
    <h4>MULTIPART DATA</h4>
    <div>
        <#list data.multiPartFormData as name, value>
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
<#if data.curl??>
    <h4>CURL</h4>
    <div>
        ${data.curl}
    </div>
</#if>
