�ҵĵ�һ��javaEE��Ŀ��ʹ��jsp&servlet������ѧ������������������򵥵Ĳ���

1-20���ٿ�ʼ��~


1-30 debug�˺ü��죬���ڽ����
request.getRequestDispatcher������ȡ����·��Ҳ����ȡ���·����ServletUser��д���˸���֮����Լ�����
reques.getPathInfo()��requrst.getServletpath�����Ǹ����Լ�servlet��urlpattern
��url Pattern��/user/*
����/Myblog/user/caterpillar
getPathInfo����=/caterpillar Ȼ����substring��1���Ϳɵõ�caterpillar
getServletPath()=/user
���ԣ�����Ҫ��ȡ����� request.getContextPath() ��·�������ǿ���ʹ�����µĴ��룺
String uri = request.getServletPath();
String pathInfo = request.getPathInfo();
if (pathInfo != null && pathInfo.length() > 0) {
    uri = uri + pathInfo;
}
servletConfig�Լ�servletContext
��jsp�����ض���application��ӦServletContext��config��ӦservletConfig
ʹ��javabean���EL��ǩ �Լ�JSTL��ǩ ����jspҳ����<%%>ʹ��