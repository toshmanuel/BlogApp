package com.blogapp.blogapp.service.cloud;

import java.io.IOException;
import java.util.Map;

public interface CloudStorageService {

    Map<Object, Object> uploadImage(Map<Object, Object> imageProperties) throws IOException;
}
