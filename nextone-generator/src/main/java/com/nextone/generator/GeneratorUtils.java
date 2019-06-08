
package com.nextone.generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class GeneratorUtils {

    public static List<Map<String, String>> list = new ArrayList<>();

    // 代码生成位置
    public static String targetProject = GeneratorUtils.class.getResource("/").getPath().replace("/target/classes/", "") + "/src/main/java/";
    //默认的包的生成路径
    public static String modelPath = "com//Generator//model";// model包的生产路径
    public static String servicePath = "com//Generator//service";// service包的生产路径
    public static String serviceImpPath = "com//Generator//serviceImp";// service包实现层的生产路径
    public static String controllerPath = "com//Generator//controller";// controller包的生产路径
    public static String mapperPath = "com//Generator//mapper";// controller包的生产路径
    //需要配置MyMapper包的路径
    public static String myMapperPath = "com//Generator//utils";// myMapper路径

//    //优化前	代码生成完成 ，耗时：575毫秒
//    public static void main(String[] args) {
//        GeneratorUtils.init();
//        GeneratorUtils.generate();
//
//    }

    /**
     * 初始化扫描启动类 主要是从generatorCRUD获取相关的配置信息到map中
     */
    public static void init() {
        URL url = GeneratorUtils.class.getClassLoader().getResource("generatorCRUD.xml");
        if (url == null) {
            throw new RuntimeException("当前路径没有generatorCRUD.xml这个文件");
        }
        File file = new File(url.getFile());
        if (file == null || !file.exists()) {
            throw new RuntimeException("当前路径没有generatorCRUD.xml这个文件");
        }
        // 拿到xml文件开始解析
        SAXReader reader = new SAXReader();
        Document document;
        try {
            document = reader.read(new FileInputStream(file));
            Element root = document.getRootElement();
            List<Element> elements = root.elements();
            // 遍历xml文件 将根目录下 每一个子目录置入map中
            for (Element e : elements) {
                // 如果当前节点不是尾节点 则继续遍历
                List<Element> elementsl = e.elements();
                for (Element el : elementsl) {
                    Map<String, String> m = new HashMap<String, String>();
                    m.put(el.getName(), el.getStringValue());
                    list.add(m);
                }
            }
        } catch (FileNotFoundException | DocumentException e1) {
            throw new RuntimeException("当前路径没有generatorCRUD.xml这个文件");
        }
        System.out.println(list);

    }

    public static void generate() {
        List<String> tableList = DataUtils.getValue(list, "tableName");// 拿到数据库表名
        List<String> modelList = DataUtils.getValue(list, "modelName");// 拿到类名 与数据库名 一 一对应
        modelPath = DataUtils.getPath(list, "model");
        servicePath = DataUtils.getPath(list, "service");
        serviceImpPath = DataUtils.getPath(list, "serviceImp");
        controllerPath = DataUtils.getPath(list, "controller");
        mapperPath = DataUtils.getPath(list, "mapper");
        File dir = new File(targetProject);// 这里将路径定义为统一模板资源路径
        if (tableList.size() != modelList.size()) {
            throw new RuntimeException("检查你的配置文件 tableName 是否与modelName 一一对应");
        }
        Configuration cfg = new Configuration();
        try {
            cfg.setDefaultEncoding("utf-8");
            cfg.setDirectoryForTemplateLoading(new File(GeneratorUtils.class.getResource("/template").getFile().toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        generateModel(cfg, tableList, modelList, dir);// 创建model包与model类的代码
        generateService(cfg, modelList, dir);// 创建service包和service类的代码
        generateController(cfg, modelList, dir);// 创建controller包和controller类的代码
        generateServiceImp(cfg, modelList, dir);// 创建service包和service类的代码
        generateMapper(cfg, modelList, dir);
        System.out.println("-----code生成完成....-------");
    }

    /*
     * generate-mapper
     */
    private static void generateMapper(Configuration cfg, List<String> modelList, File dir) {
        Map<String, Object> rootMap = new HashMap<String, Object>();
        List<String> mapperNameList = DataUtils.dealClassNameByParam(modelList, "Mapper");// 每一个model类增加后缀名
        List<String> mapperNameListSuffix = DataUtils.dealClassName(mapperNameList);// 添加后缀名
        Writer docout = null;
        try {
            for (int i = 0; i < modelList.size(); i++) {
                Template temp = cfg.getTemplate("MapperTemplateImp.java");
                File documentFile = new File(dir + "//" + mapperPath);
                if (!documentFile.exists()) {
                    documentFile.mkdir();
                }
                File docFile = new File(documentFile + "//" + mapperNameListSuffix.get(i));
                docout = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
                rootMap.put("myMapperPath", myMapperPath.replace("//", "."));
                rootMap.put("className", mapperNameList.get(i));
                rootMap.put("model", modelList.get(i));
                rootMap.put("package", mapperPath.replace("//", "."));
                rootMap.put("modelPath", modelPath.replace("//", "."));

                temp.process(rootMap, docout);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } finally {
            try {
                if (docout != null) {
                    docout.flush();
                    docout.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * generate-serviceImp
     *
     * @param cfg
     * @param modelList
     * @param dir
     */
    private static void generateServiceImp(Configuration cfg, List<String> modelList, File dir) {
        Map<String, Object> rootMap = new HashMap<String, Object>();
        List<String> serviceImpNameList = DataUtils.dealClassNameByParam(modelList, "ServiceImp");// 每一个model类增加后缀名
        List<String> serviceImpNameListSuffix = DataUtils.dealClassName(serviceImpNameList);// 添加后缀名
        List<String> serviceNameList = DataUtils.dealClassNameByParam(modelList, "Service");// 每一个model类增加后缀名
        List<String> mapperNameList = DataUtils.dealClassNameByParam(modelList, "Mapper");// 每一个model类增加后缀名
        Writer docout = null;
        try {
            for (int i = 0; i < modelList.size(); i++) {
                Template temp = cfg.getTemplate("ServiceTemplateImp.java");
                File documentFile = new File(dir + "//" + serviceImpPath);
                if (!documentFile.exists()) {
                    documentFile.mkdir();
                }
                File docFile = new File(documentFile + "//" + serviceImpNameListSuffix.get(i));
                docout = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
                rootMap.put("className", serviceImpNameList.get(i));
                rootMap.put("serviceName", serviceNameList.get(i));
                rootMap.put("package", serviceImpPath.replace("//", "."));
                rootMap.put("servicePath", servicePath.replace("//", "."));
                rootMap.put("model", modelList.get(i));
                rootMap.put("modelPath", modelPath.replace("//", "."));
                rootMap.put("mapperPath", mapperPath.replace("//", "."));
                rootMap.put("mapperName", mapperNameList.get(i));
                temp.process(rootMap, docout);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } finally {
            try {
                if (docout != null) {
                    docout.flush();
                    docout.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * generate-controller
     *
     * @param cfg
     * @param modelList
     * @param dir
     */
    private static void generateController(Configuration cfg, List<String> modelList, File dir) {
        Map<String, Object> rootMap = new HashMap<String, Object>();
        List<String> controllerNameList = DataUtils.dealClassNameByParam(modelList, "Controller");// 每一个model类增加后缀名
        List<String> controllerNameListSuffix = DataUtils.dealClassName(controllerNameList);
        List<String> serviceNameList = DataUtils.dealClassNameByParam(modelList, "Service");// 每一个model类增加后缀名
        Writer docout = null;
        try {
            for (int i = 0; i < modelList.size(); i++) {

                Template temp = cfg.getTemplate("ControllerTemplate.java");
                File documentFile = new File(dir + "//" + controllerPath);
                if (!documentFile.exists()) {
                    documentFile.mkdir();
                }
                File docFile = new File(documentFile + "//" + controllerNameListSuffix.get(i));
                docout = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
                rootMap.put("className", controllerNameList.get(i));
                rootMap.put("model", modelList.get(i));
                rootMap.put("requestMapping", modelList.get(i).toLowerCase());
                rootMap.put("serviceName", serviceNameList.get(i));
                rootMap.put("package", controllerPath.replace("//", "."));
                rootMap.put("modelPath", modelPath.replace("//", "."));
                rootMap.put("servicePath", servicePath.replace("//", "."));
                temp.process(rootMap, docout);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } finally {
            try {
                if (docout != null) {
                    docout.flush();
                    docout.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * generate-service
     *
     * @param cfg
     * @param modelList
     * @param dir
     */
    private static void generateService(Configuration cfg, List<String> modelList, File dir) {
        List<String> serviceNameList = DataUtils.dealClassNameByParam(modelList, "Service");// 每一个model类增加后缀名
        List<String> serviceListSuffix = DataUtils.dealClassName(serviceNameList);
        Map<String, Object> rootMap = new HashMap<String, Object>();
        Writer docout = null;
        try {
            for (int i = 0; i < modelList.size(); i++) {
                Template temp = cfg.getTemplate("ServiceTemplate.java");
                File documentFile = new File(dir + "//" + servicePath);
                if (!documentFile.exists()) {
                    documentFile.mkdir();
                }
                File docFile = new File(documentFile + "//" + serviceListSuffix.get(i));
                rootMap.put("package", servicePath.replace("//", "."));
                rootMap.put("className", serviceNameList.get(i));
                rootMap.put("modelName", modelList.get(i));
                rootMap.put("modelPath", modelPath.replace("//", "."));
                docout = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
                temp.process(rootMap, docout);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } finally {
            try {
                if (docout != null) {
                    docout.flush();
                    docout.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * generate-model
     *
     * @param cfg
     * @param tableList
     * @param modelList
     * @param dir
     */
    private static void generateModel(Configuration cfg, List<String> tableList, List<String> modelList, File dir) {
        List<String> modelListSuffix = DataUtils.dealClassName(modelList);
        Map<String, Object> rootMap = new HashMap<String, Object>();
        Map<String, String> columnMap = new HashMap<String, String>();
        Writer docout = null;
        try {
            if (!dir.exists()) {
                dir.mkdirs();
            }
            for (int i = 0; i < modelList.size(); i++) {
                Template temp = cfg.getTemplate("ModelTemplate.java");
                File documentFile = new File(dir + "//" + modelPath);
                if (!documentFile.exists()) {
                    documentFile.mkdir();
                }
                File docFile = new File(documentFile + "//" + modelListSuffix.get(i));
                columnMap = DataUtils.getColumnMap(tableList.get(i));
                columnMap = DataUtils.formateMap(columnMap);
                rootMap.put("columnMap", columnMap);
                rootMap.put("className", modelList.get(i));
                rootMap.put("package", modelPath.replace("//", "."));
                docout = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
                temp.process(rootMap, docout);

            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("IO 异常");
        } catch (TemplateException e) {
            e.printStackTrace();
            throw new RuntimeException("模板 异常");
        } finally {
            try {
                if (docout != null) {
                    docout.flush();
                    docout.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

