package com.jiemi.iApp;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodHook.MethodHookParam;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Hook implements IXposedHookLoadPackage {
    public HashMap args;
    public String bao;
    public char[][] bb;
    public String cd = "/sdcard/iApp动态/";
    public int f;
    public File file;
    public FileOutputStream fos = ((FileOutputStream) null);
    public XSharedPreferences gg = new XSharedPreferences("com.jiemi.iApp", "data");
    public String h3 = ((String) null);
    public String h4 = ((String) null);
    public String h7 = ((String) null);
    public int kaiguan = 0;
    public int kaiguan1 = 0;
    public String kaiguan2 = "";
    public int kaiguan3 = 0;
    public PrintStream ps = ((PrintStream) null);
    public String[] string;

    /* renamed from: com.jiemi.iApp.Hook$100000002 */
    class AnonymousClass100000002 extends XC_MethodHook {
        private final Hook this$0;
        private final Class val$v;

        AnonymousClass100000002(Hook hook, Class cls) {
            this.this$0 = hook;
            this.val$v = cls;
        }

        static Hook access$0(AnonymousClass100000002 anonymousClass100000002) {
            return anonymousClass100000002.this$0;
        }

        /* Access modifiers changed, original: protected */
        public void afterHookedMethod(MethodHookParam methodHookParam) throws Throwable {
            this.this$0.fils((String) methodHookParam.args[1], (HashMap) XposedHelpers.getStaticObjectField(this.val$v, "c"), ".myu", "fn ", "\nend fn");
            super.afterHookedMethod(methodHookParam);
        }
    }

    public String b(char[] cArr, int i) {
        char[] cArr2 = new char[cArr.length];
        for (int i2 = 0; i2 < cArr.length; i2++) {
            cArr2[i2] = (char) (cArr[i2] ^ i);
        }
        return String.valueOf(cArr2);
    }

    public void file(String str, String str2, boolean z) {
        this.file = new File(new StringBuffer().append(new StringBuffer().append(new StringBuffer().append(this.cd).append(this.bao).toString()).append("/").toString()).append(str).toString());
        try {
            this.fos = new FileOutputStream(this.file, z);
            this.ps = new PrintStream(this.fos);
            this.ps.print(str2);
            this.ps.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void fils(String str, HashMap hashMap, String str2, String str3, String str4) {
        this.file = new File(new StringBuffer().append(new StringBuffer().append(new StringBuffer().append(this.cd).append(this.bao).toString()).append("/").toString()).append(str).toString());
        Set keySet = hashMap.keySet();
        if (!this.file.exists()) {
            Iterator it = keySet.iterator();
            while (it.hasNext()) {
                Object next = it.next();
                String[] split = next.toString().split("\\.");
                if (this.kaiguan2.indexOf(next.toString()) == -1) {
                    this.kaiguan2 = new StringBuffer().append(this.kaiguan2).append(next).toString();
                    file(new StringBuffer().append(split[0]).append(str2).toString(), new StringBuffer().append(new StringBuffer().append(str3).append(hashMap.get(next).toString()).toString()).append(str4).toString(), true);
                }
            }
        }
        this.kaiguan2 = new StringBuffer().append(this.kaiguan2).append(keySet).toString();
    }

    public void hook(ClassLoader classLoader, int i) {
        this.string = new String[this.bb.length];
        for (int i2 = 0; i2 < this.bb.length; i2++) {
            this.string[i2] = b(this.bb[i2], i);
        }
        Class findClass = XposedHelpers.findClass(this.string[0], classLoader);
        Class findClass2 = XposedHelpers.findClass(this.string[1], classLoader);
        Class findClass3 = XposedHelpers.findClass(this.string[2], classLoader);
        Class findClass4 = XposedHelpers.findClass(this.string[3], classLoader);
        String str = "h4";
        Object[] objArr = new Object[4];
        try {
            objArr[0] = Class.forName("android.content.Context");
            try {
                objArr[1] = Class.forName("java.lang.String");
                try {
                    objArr[2] = Class.forName("[Ljava.lang.Object;");
                    objArr[3] = new XC_MethodHook(this) {
                        private final Hook this$0;

                        {
                            this.this$0 = r1;
                        }

                        static Hook access$0(AnonymousClass100000000 anonymousClass100000000) {
                            return anonymousClass100000000.this$0;
                        }

                        /* Access modifiers changed, original: protected */
                        public void beforeHookedMethod(MethodHookParam methodHookParam) throws Throwable {
                            this.this$0.h4 = (String) methodHookParam.args[1];
                            this.this$0.file = new File(new StringBuffer().append(new StringBuffer().append(new StringBuffer().append(this.this$0.cd).append(this.this$0.bao).toString()).append("/").toString()).append(this.this$0.h4).toString());
                            if (this.this$0.file.exists()) {
                                this.this$0.kaiguan = 0;
                            } else if (this.this$0.kaiguan2.indexOf(this.this$0.h4) != -1) {
                                this.this$0.kaiguan = 0;
                            } else {
                                StringBuffer stringBuffer = new StringBuffer();
                                Hook hook = this.this$0;
                                hook.kaiguan2 = stringBuffer.append(hook.kaiguan2).append(this.this$0.h4).toString();
                                this.this$0.kaiguan = 1;
                            }
                            super.beforeHookedMethod(methodHookParam);
                        }
                    };
                    XposedHelpers.findAndHookMethod(findClass3, str, objArr);
                    str = "com.iapp.app.e";
                    String str2 = "ah";
                    Object[] objArr2 = new Object[3];
                    try {
                        objArr2[0] = Class.forName("[Ljava.lang.Object;");
                        try {
                            objArr2[1] = Class.forName("java.lang.String");
                            objArr2[2] = new XC_MethodHook(this) {
                                private final Hook this$0;

                                {
                                    this.this$0 = r1;
                                }

                                static Hook access$0(AnonymousClass100000001 anonymousClass100000001) {
                                    return anonymousClass100000001.this$0;
                                }

                                /* Access modifiers changed, original: protected */
                                public void beforeHookedMethod(MethodHookParam methodHookParam) throws Throwable {
                                    String stringBuffer = new StringBuffer().append((String) methodHookParam.args[1]).append("</View>").toString();
                                    if (this.this$0.kaiguan == 1) {
                                        this.this$0.file(this.this$0.h4, stringBuffer, true);
                                    }
                                    super.beforeHookedMethod(methodHookParam);
                                }
                            };
                            XposedHelpers.findAndHookMethod(str, classLoader, str2, objArr2);
                            str = "l";
                            objArr = new Object[3];
                            try {
                                objArr[0] = Class.forName("android.content.Context");
                                try {
                                    objArr[1] = Class.forName("java.lang.String");
                                    objArr[2] = new AnonymousClass100000002(this, findClass2);
                                    XposedHelpers.findAndHookMethod(findClass4, str, objArr);
                                    String str3 = "k";
                                    Object[] objArr3 = new Object[3];
                                    try {
                                        objArr3[0] = Class.forName("android.content.Context");
                                        try {
                                            objArr3[1] = Class.forName("java.lang.String");
                                            objArr3[2] = new XC_MethodHook(this) {
                                                private final Hook this$0;

                                                {
                                                    this.this$0 = r1;
                                                }

                                                static Hook access$0(AnonymousClass100000003 anonymousClass100000003) {
                                                    return anonymousClass100000003.this$0;
                                                }

                                                /* Access modifiers changed, original: protected */
                                                public void afterHookedMethod(MethodHookParam methodHookParam) throws Throwable {
                                                    String str = (String) methodHookParam.getResult();
                                                    if (this.this$0.kaiguan == 1 && str != null) {
                                                        this.this$0.file(this.this$0.h4, new StringBuffer().append(new StringBuffer().append("<UIEventset><eventItme type=\"loading\">").append(str).toString()).append("</eventItme></UIEventset>").toString(), true);
                                                    }
                                                    super.afterHookedMethod(methodHookParam);
                                                }
                                            };
                                            XposedHelpers.findAndHookMethod(findClass4, str3, objArr3);
                                            str3 = "h3";
                                            Object[] objArr4 = new Object[3];
                                            try {
                                                objArr4[0] = Class.forName("android.content.Context");
                                                try {
                                                    objArr4[1] = Class.forName("java.lang.String");
                                                    objArr4[2] = new XC_MethodHook(this) {
                                                        private final Hook this$0;

                                                        {
                                                            this.this$0 = r1;
                                                        }

                                                        static Hook access$0(AnonymousClass100000004 anonymousClass100000004) {
                                                            return anonymousClass100000004.this$0;
                                                        }

                                                        /* Access modifiers changed, original: protected */
                                                        public void beforeHookedMethod(MethodHookParam methodHookParam) throws Throwable {
                                                            this.this$0.h3 = (String) methodHookParam.args[1];
                                                            this.this$0.file = new File(new StringBuffer().append(new StringBuffer().append(new StringBuffer().append(this.this$0.cd).append(this.this$0.bao).toString()).append("/").toString()).append(this.this$0.h3).toString());
                                                            if (this.this$0.file.exists()) {
                                                                this.this$0.kaiguan1 = 0;
                                                            } else if (this.this$0.kaiguan2.indexOf(this.this$0.h3) != -1) {
                                                                this.this$0.kaiguan1 = 0;
                                                            } else {
                                                                StringBuffer stringBuffer = new StringBuffer();
                                                                Hook hook = this.this$0;
                                                                hook.kaiguan2 = stringBuffer.append(hook.kaiguan2).append(this.this$0.h3).toString();
                                                                this.this$0.kaiguan1 = 1;
                                                            }
                                                            super.beforeHookedMethod(methodHookParam);
                                                        }
                                                    };
                                                    XposedHelpers.findAndHookMethod(findClass3, str3, objArr4);
                                                    str3 = "bsh.Interpreter";
                                                    String str4 = "eval";
                                                    objArr3 = new Object[2];
                                                    try {
                                                        objArr3[0] = Class.forName("java.io.Reader");
                                                        objArr3[1] = new XC_MethodHook(this) {
                                                            private final Hook this$0;

                                                            {
                                                                this.this$0 = r1;
                                                            }

                                                            static Hook access$0(AnonymousClass100000005 anonymousClass100000005) {
                                                                return anonymousClass100000005.this$0;
                                                            }

                                                            /* Access modifiers changed, original: protected */
                                                            public void beforeHookedMethod(MethodHookParam methodHookParam) throws Throwable {
                                                                StringReader stringReader = (StringReader) methodHookParam.args[0];
                                                                char[] cArr = new char[1024];
                                                                while (true) {
                                                                    int read = stringReader.read(cArr);
                                                                    if (read == -1) {
                                                                        stringReader.close();
                                                                        super.beforeHookedMethod(methodHookParam);
                                                                        return;
                                                                    } else if (this.this$0.kaiguan3 == 1) {
                                                                        this.this$0.file(this.this$0.h7, new String(cArr, 0, read), true);
                                                                    }
                                                                }
                                                            }
                                                        };
                                                        XposedHelpers.findAndHookMethod(str3, classLoader, str4, objArr3);
                                                        str3 = "h7";
                                                        objArr4 = new Object[4];
                                                        try {
                                                            objArr4[0] = Class.forName("android.content.Context");
                                                            try {
                                                                objArr4[1] = Class.forName("java.lang.Object");
                                                                try {
                                                                    objArr4[2] = Class.forName("java.lang.String");
                                                                    objArr4[3] = new XC_MethodHook(this) {
                                                                        private final Hook this$0;

                                                                        {
                                                                            this.this$0 = r1;
                                                                        }

                                                                        static Hook access$0(AnonymousClass100000006 anonymousClass100000006) {
                                                                            return anonymousClass100000006.this$0;
                                                                        }

                                                                        /* Access modifiers changed, original: protected */
                                                                        public void beforeHookedMethod(MethodHookParam methodHookParam) throws Throwable {
                                                                            this.this$0.h7 = (String) methodHookParam.args[2];
                                                                            this.this$0.file = new File(new StringBuffer().append(new StringBuffer().append(new StringBuffer().append(this.this$0.cd).append(this.this$0.bao).toString()).append("/").toString()).append(this.this$0.h7).toString());
                                                                            if (this.this$0.file.exists()) {
                                                                                this.this$0.kaiguan3 = 0;
                                                                            } else {
                                                                                this.this$0.kaiguan3 = 1;
                                                                            }
                                                                            super.beforeHookedMethod(methodHookParam);
                                                                        }
                                                                    };
                                                                    XposedHelpers.findAndHookMethod(findClass3, str3, objArr4);
                                                                    str3 = "g";
                                                                    Object[] objArr5 = new Object[2];
                                                                    try {
                                                                        objArr5[0] = Class.forName("java.lang.String");
                                                                        objArr5[1] = new XC_MethodHook(this) {
                                                                            private final Hook this$0;

                                                                            {
                                                                                this.this$0 = r1;
                                                                            }

                                                                            static Hook access$0(AnonymousClass100000007 anonymousClass100000007) {
                                                                                return anonymousClass100000007.this$0;
                                                                            }

                                                                            /* Access modifiers changed, original: protected */
                                                                            public void beforeHookedMethod(MethodHookParam methodHookParam) throws Throwable {
                                                                                String stringBuffer = new StringBuffer().append((String) methodHookParam.args[0]).append("</View>").toString();
                                                                                if (this.this$0.kaiguan1 == 1) {
                                                                                    this.this$0.file(this.this$0.h3, stringBuffer, true);
                                                                                }
                                                                                super.beforeHookedMethod(methodHookParam);
                                                                            }
                                                                        };
                                                                        XposedHelpers.findAndHookMethod(findClass, str3, objArr5);
                                                                        XposedHelpers.findAndHookMethod(findClass, "g", new Object[]{new XC_MethodHook(this) {
                                                                            private final Hook this$0;

                                                                            {
                                                                                this.this$0 = r1;
                                                                            }

                                                                            static Hook access$0(AnonymousClass100000008 anonymousClass100000008) {
                                                                                return anonymousClass100000008.this$0;
                                                                            }

                                                                            /* Access modifiers changed, original: protected */
                                                                            public void beforeHookedMethod(MethodHookParam methodHookParam) throws Throwable {
                                                                                String stringBuffer = new StringBuffer().append(new StringBuffer().append("<UIEventset>").append((String) XposedHelpers.getObjectField(methodHookParam.thisObject, "r")).toString()).append("</UIEventset>").toString();
                                                                                if (this.this$0.kaiguan1 == 1) {
                                                                                    this.this$0.file(this.this$0.h3, stringBuffer, true);
                                                                                }
                                                                                super.beforeHookedMethod(methodHookParam);
                                                                            }
                                                                        }});
                                                                    } catch (ClassNotFoundException e) {
                                                                        throw new NoClassDefFoundError(e.getMessage());
                                                                    }
                                                                } catch (ClassNotFoundException e2) {
                                                                    throw new NoClassDefFoundError(e2.getMessage());
                                                                }
                                                            } catch (ClassNotFoundException e22) {
                                                                throw new NoClassDefFoundError(e22.getMessage());
                                                            }
                                                        } catch (ClassNotFoundException e222) {
                                                            throw new NoClassDefFoundError(e222.getMessage());
                                                        }
                                                    } catch (ClassNotFoundException e2222) {
                                                        throw new NoClassDefFoundError(e2222.getMessage());
                                                    }
                                                } catch (ClassNotFoundException e22222) {
                                                    throw new NoClassDefFoundError(e22222.getMessage());
                                                }
                                            } catch (ClassNotFoundException e222222) {
                                                throw new NoClassDefFoundError(e222222.getMessage());
                                            }
                                        } catch (ClassNotFoundException e2222222) {
                                            throw new NoClassDefFoundError(e2222222.getMessage());
                                        }
                                    } catch (ClassNotFoundException e22222222) {
                                        throw new NoClassDefFoundError(e22222222.getMessage());
                                    }
                                } catch (ClassNotFoundException e222222222) {
                                    throw new NoClassDefFoundError(e222222222.getMessage());
                                }
                            } catch (ClassNotFoundException e2222222222) {
                                throw new NoClassDefFoundError(e2222222222.getMessage());
                            }
                        } catch (ClassNotFoundException e22222222222) {
                            throw new NoClassDefFoundError(e22222222222.getMessage());
                        }
                    } catch (ClassNotFoundException e222222222222) {
                        throw new NoClassDefFoundError(e222222222222.getMessage());
                    }
                } catch (ClassNotFoundException e2222222222222) {
                    throw new NoClassDefFoundError(e2222222222222.getMessage());
                }
            } catch (ClassNotFoundException e22222222222222) {
                throw new NoClassDefFoundError(e22222222222222.getMessage());
            }
        } catch (ClassNotFoundException e222222222222222) {
            throw new NoClassDefFoundError(e222222222222222.getMessage());
        }
    }

    public void handleLoadPackage(LoadPackageParam loadPackageParam) throws Throwable {
        this.gg.reload();
        this.f = this.gg.getInt("a", 0);
        this.bao = this.gg.getString("data", (String) null);
        if (loadPackageParam.packageName.equals(this.bao)) {
            this.file = new File(new StringBuffer().append(this.cd).append(this.bao).toString());
            if (!this.file.exists()) {
                this.file.mkdirs();
            }
            hook(loadPackageParam.classLoader, this.f);
        }
    }

    public Hook() {
        r0 = new char[4][];
        r0[0] = new char[]{(char) 15983, (char) 15971, (char) 15969, (char) 15906, (char) 15973, (char) 15981, (char) 15996, (char) 15996, (char) 15906, (char) 15981, (char) 15996, (char) 15996, (char) 15906, (char) 15998, (char) 15993, (char) 15970, (char) 15906, (char) 15969, (char) 15973, (char) 15981, (char) 15970};
        r0[1] = new char[]{(char) 15983, (char) 15971, (char) 15969, (char) 15906, (char) 15982, (char) 15906, (char) 15981, (char) 15906, (char) 15981, (char) 15906, (char) 15998};
        r0[2] = new char[]{(char) 15983, (char) 15971, (char) 15969, (char) 15906, (char) 15973, (char) 15981, (char) 15996, (char) 15996, (char) 15906, (char) 15981, (char) 15996, (char) 15996, (char) 15906, (char) 15982};
        r0[3] = new char[]{(char) 15983, (char) 15971, (char) 15969, (char) 15906, (char) 15982, (char) 15906, (char) 15981, (char) 15906, (char) 15981, (char) 15906, (char) 15972};
        this.bb = r0;
    }
}
