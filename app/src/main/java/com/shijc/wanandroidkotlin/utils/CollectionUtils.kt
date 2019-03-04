package com.shijc.wanandroidkotlin.utils

import java.util.LinkedHashMap

/**
 * @Package com.shijc.wanandroidkotlin.utils
 * @Description:
 * @author shijiacheng
 * @date 2019/2/26 上午 10:38
 * @version V1.0
 */
class CollectionUtils {
    companion object {
        /**
         * 判断数组是否为NULL或者为空
         * @param objs    需要判断的对象数组
         * @return        如果被检测的对象数组为NULL或者为空则返回true，否则返回false
         */
        fun isEmpty(objs: Array<Any>?): Boolean {
            return objs == null || objs.size <= 0
        }

        /**
         * 判断列表容器是否为NULL或者没有元素
         * @param list    需要判断的列表容器
         * @return        如果被检测的列表容器为NULL或者为空，则返回true，否则返回false
         */
        fun <E> isEmpty(list: List<E>?): Boolean {
            return list == null || list.size <= 0
        }

        /**
         * 判断Map容器是否为NULL或者没有元素
         * @param <K></K>, V>
         * @param map    需要判断的Map容器
         * @return        如果被检测的Map为NULL或者为空，则返回true，否则返回false
         */
        fun <K, V> isEmpty(map: Map<K, V>?): Boolean {
            return map == null || map.size <= 0
        }

        /**
         * 判断Map容器是否为NULL或者没有元素
         * @param map    需要判断的Map容器
         * @return        如果被检测的Map为NULL或者为空，则返回true，否则返回false
         */
        fun isEmpty(map: LinkedHashMap<String, String>?): Boolean {
            return map == null || map.size <= 0
        }
    }


}