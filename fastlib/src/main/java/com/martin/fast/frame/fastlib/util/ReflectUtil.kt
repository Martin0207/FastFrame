package com.martin.fast.frame.fastlib.util

import com.martin.fast.frame.fastlib.BuildConfig
import timber.log.Timber
import java.lang.reflect.Constructor
import java.lang.reflect.Field
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method


/**
 * @author ：Martin
 * @date : 2018/6/26 15:42
 */
object ReflectUtil {

    /**
     *
     * @title: setField
     * @description: 设置某个成员遍历的值
     * @param owner
     * @param fieldName
     * @param value
     * @throws Exception
     * @return: void
     */
    @Throws(Exception::class)
    fun setField(owner: Any, fieldName: String, value: Any) {
        val ownerClass = owner.javaClass
        val field = ownerClass.getDeclaredField(fieldName)
        field.isAccessible = true
        field.set(owner, value)
    }

    /**
     *
     * @title: setFieldAll
     * @description: 可以设置父类的field的值
     * @param owner
     * @param fieldName
     * @param value
     * @throws Exception
     * @return: void
     */
    @Throws(Exception::class)
    fun setFieldAll(owner: Any, fieldName: String, value: Any) {
        val ownerClass = owner.javaClass
        var field: Field? = null
        var clazz = ownerClass
        while (clazz != Any::class.java) {
            try {
                field = clazz.getDeclaredField(fieldName)
                Timber.d(field.toString() + " find : in " + clazz.name)
                break
            } catch (e: Exception) {
                Timber.d(fieldName + " not find in " + clazz.name)
            }

            clazz = clazz.getSuperclass()
        }
        field!!.isAccessible = true
        field.set(owner, value)
    }

    /**
     * 得到某个对象的公共属性
     *
     * @param owner
     * , fieldName
     * @return 该属性对象
     * @throws Exception
     */
    @Throws(Exception::class)
    fun getField(owner: Any, fieldName: String): Any {
        val ownerClass = owner.javaClass

        val field = ownerClass.getField(fieldName)

        return field.get(owner)
    }

    /**
     * 得到某类的静态公共属性
     *
     * @param className
     * 类名
     * @param fieldName
     * 属性名
     * @return 该属性对象
     * @throws Exception
     */
    @Throws(Exception::class)
    fun getStaticField(className: String, fieldName: String): Any {
        val ownerClass = Class.forName(className)

        val field = ownerClass.getField(fieldName)

        return field.get(ownerClass)
    }

    /**
     * 执行某对象方法
     *
     * @param owner
     * 对象
     * @param methodName
     * 方法名
     * @param args
     * 参数
     * @return 方法返回值
     * @throws Exception
     */
    @Throws(Exception::class)
    fun invokeMethod(owner: Any, methodName: String, vararg args: Any): Any {

        val ownerClass = owner.javaClass

        val argsClass = arrayOfNulls<Class<*>>(args.size)

        var i = 0
        val j = args.size
        while (i < j) {
            if (args[i].javaClass == Int::class.java) { //一般的函数都是 int 而不是Integer
                argsClass[i] = Int::class.javaPrimitiveType
            } else if (args[i].javaClass == Float::class.java) { //一般的函数都是 int 而不是Integer
                argsClass[i] = Float::class.javaPrimitiveType
            } else if (args[i].javaClass == Double::class.java) { //一般的函数都是 int 而不是Integer
                argsClass[i] = Double::class.javaPrimitiveType
            } else {
                argsClass[i] = args[i].javaClass
            }
            i++
        }

        val method = ownerClass.getDeclaredMethod(methodName, *argsClass)
        method.isAccessible = true
        return method.invoke(owner, args)
    }

    /**
     *
     * @title: invokeMethodAll
     * @description: 调用所有的函数, 包括父类的所有函数
     * @param owner
     * @param methodName
     * @param args
     * @return
     * @throws Exception
     * @return: Object
     */
    @Throws(Exception::class)
    fun invokeMethodAll(owner: Any, methodName: String, vararg args: Any): Any? {

        val ownerClass = owner.javaClass

        val argsClass = arrayOfNulls<Class<*>>(args.size)

        var i = 0
        val j = args.size
        while (i < j) {
            if (args[i].javaClass == Int::class.java) { //一般的函数都是 int 而不是Integer
                argsClass[i] = Int::class.javaPrimitiveType
            } else if (args[i].javaClass == Float::class.java) { //一般的函数都是 int 而不是Integer
                argsClass[i] = Float::class.javaPrimitiveType
            } else if (args[i].javaClass == Double::class.java) { //一般的函数都是 int 而不是Integer
                argsClass[i] = Double::class.javaPrimitiveType
            } else {
                argsClass[i] = args[i].javaClass
            }
            i++
        }
        var method: Method? = null

        var clazz = ownerClass
        while (clazz != Any::class.java) {
            try {
                method = clazz.getDeclaredMethod(methodName, *argsClass)
                Timber.d(method.toString() + " find : in " + clazz.name)
                return method
            } catch (e: Exception) {
                //e.printStackTrace();
                Timber.d(methodName + " not find in " + clazz.name)
            }

            clazz = clazz.getSuperclass()
        }
        method!!.setAccessible(true)
        return method.invoke(owner, args)
    }

    /**
     * 执行某类的静态方法
     *
     * @param className
     * 类名
     * @param methodName
     * 方法名
     * @param args
     * 参数数组
     * @return 执行方法返回的结果
     * @throws Exception
     */
    @Throws(Exception::class)
    fun invokeStaticMethod(className: String, methodName: String, vararg args: Any): Any {
        val ownerClass = Class.forName(className)

        val argsClass = arrayOfNulls<Class<*>>(args.size)

        var i = 0
        val j = args.size
        while (i < j) {
            argsClass[i] = args[i].javaClass
            i++
        }

        val method = ownerClass.getMethod(methodName, *argsClass)
        method.isAccessible = true
        return method.invoke(null, args)
    }

    /**
     * 新建实例
     *
     * @param className
     * 类名
     * @param args
     * 构造函数的参数 如果无构造参数，args 填写为 null
     * @return 新建的实例
     * @throws Exception
     */
    @Throws(NoSuchMethodException::class, SecurityException::class, ClassNotFoundException::class,
            InstantiationException::class, IllegalAccessException::class, IllegalArgumentException::class,
            InvocationTargetException::class)
    fun newInstance(className: String, args: Array<Any>?): Any {
        return newInstance(className, args, null)

    }

    /**
     * 新建实例
     *
     * @param className
     * 类名
     * @param args
     * 构造函数的参数 如果无构造参数，args 填写为 null
     * @return 新建的实例
     * @throws Exception
     */
    @Throws(NoSuchMethodException::class, SecurityException::class, ClassNotFoundException::class,
            InstantiationException::class, IllegalAccessException::class, IllegalArgumentException::class,
            InvocationTargetException::class)
    fun newInstance(className: String, args: Array<Any>?, argsType: Array<Class<*>>?): Any {
        val newoneClass = Class.forName(className)

        if (args == null) {
            return newoneClass.newInstance()

        } else {
            val cons: Constructor<*>
            if (argsType == null) {
                val argsClass = arrayOfNulls<Class<*>>(args.size)

                var i = 0
                val j = args.size
                while (i < j) {
                    argsClass[i] = args[i].javaClass
                    i++
                }

                cons = newoneClass.getConstructor(*argsClass)
            } else {
                cons = newoneClass.getConstructor(*argsType)
            }
            return cons.newInstance(args)
        }

    }

    /**
     * 是不是某个类的实例
     *
     * @param obj
     * 实例
     * @param cls
     * 类
     * @return 如果 obj 是此类的实例，则返回 true
     */
    fun isInstance(obj: Any, cls: Class<*>): Boolean {
        return cls.isInstance(obj)
    }

    /**
     *
     * @title: GetClassListByPackage
     * @description: 获取包下的所有Class
     * @param pPackage
     * @return
     * @return: Class
     */
    fun getClassListByPackage(pPackage: String): Class<*> {
        val _Package = Package.getPackage(pPackage)
        return _Package.javaClass
    }
}