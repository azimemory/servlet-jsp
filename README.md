# servlet

servlet 수업자료

#TOMCAT의 context.xml 파일에 아래 Resources 태그를 추가해 외부 경로를 URL로 접근할 수 있도록 처리해주자. 

 <Resources>
    <PreResources className="org.apache.catalina.webresources.DirResourceSet" webAppMount="your URL PATH" base="your upload directory" />
 </Resources>
