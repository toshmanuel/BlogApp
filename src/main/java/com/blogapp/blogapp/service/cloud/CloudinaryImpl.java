package com.blogapp.blogapp.service.cloud;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;


@Service
public class CloudinaryImpl implements CloudStorageService{

    Cloudinary cloudinary;

    public CloudinaryImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public Map<Object, Object> uploadImage(Map<Object, Object> imageProperties) throws IOException {
        cloudinary.uploader().upload(imageProperties.get("file"), ObjectUtils.emptyMap());
        return null;
    }
}
