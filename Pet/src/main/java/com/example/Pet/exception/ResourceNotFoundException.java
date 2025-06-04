package com.example.Pet.exception;

public class ResourceNotFoundException extends RuntimeException {
    String resourceName;
    String fieldName;
    long fieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, long filedValue) {

        super(String.format("%s not found with %s : %d",resourceName, fieldName,filedValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = filedValue;
    }
    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public long getFiledValue() {
        return fieldValue;
    }

    public void setFiledValue(long filedValue) {
        this.fieldValue = filedValue;
    }
    }

