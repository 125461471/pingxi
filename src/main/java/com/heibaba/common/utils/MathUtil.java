package com.heibaba.common.utils;

import java.math.BigDecimal;

public class MathUtil {

    private MathUtil() {
    	
    }  
    
    /**
     * 四舍五入
     * @param d
     * @param scale
     * @return
     */
    public static double roud(double d, int scale) {  
    	
        if(scale < 0){  
            throw new IllegalArgumentException("精度必须为大于等于0的正整数");  
        }  
        BigDecimal b = new BigDecimal(d);  
        
        return b.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();  
    } 
    
    /**
     * 四舍五入
     * @param d
     * @param scale
     * @return
     */
    public static double roud(BigDecimal b, int scale) {  
    	
        if(scale < 0){  
            throw new IllegalArgumentException("精度必须为大于等于0的正整数");  
        }  
        
        return b.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();  
    } 
    
    /**
     * 加法
     * @param d1
     * @param d2
     * @return
     */
    public static double add(double d1, double d2) {  
    	
        BigDecimal b1 = new BigDecimal(d1);  
        BigDecimal b2 = new BigDecimal(d2);  
        
        return b1.add(b2).doubleValue();  
    }  
    
    /**
     * 减法
     * @param d1
     * @param d2
     * @return
     */
    public static double subtract(double d1, double d2) {  
    	
        BigDecimal b1 = new BigDecimal(d1);  
        BigDecimal b2 = new BigDecimal(d2);  
        
        return b1.subtract(b2).doubleValue();  
    }  
      
    /**
     * 乘法
     * @param d1
     * @param d2
     * @return
     */
    public static double multiply(double d1, double d2) {  
    	
        BigDecimal b1 = new BigDecimal(d1);  
        BigDecimal b2 = new BigDecimal(d2);  
        
        return b1.multiply(b2).doubleValue();  
    }  
      
    /**
     * 除法
     * @param d1
     * @param d2
     * @return
     */
    public static double divide(double d1, double d2, int scale) {  
    	
        if(scale < 0){  
            throw new IllegalArgumentException("精度必须为大于等于0的正整数");  
        }  
        BigDecimal b1 = new BigDecimal(d1);  
        BigDecimal b2 = new BigDecimal(d2);  
        
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue(); 
    }
}
