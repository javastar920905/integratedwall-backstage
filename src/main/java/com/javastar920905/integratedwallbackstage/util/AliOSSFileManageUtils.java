package com.javastar920905.integratedwallbackstage.util;


import com.aliyun.oss.model.ListObjectsRequest;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;

import java.util.ArrayList;
import java.util.List;

import static com.javastar920905.integratedwallbackstage.util.AliOSSConstants.bucketName;


/**
 * @author ouzhx on 2018/6/5.
 *
 *         阿里云OSS 文件管理工具类
 * 
 */
public class AliOSSFileManageUtils {



  /**
   * 列举指定个数的文件
   *
   * https://help.aliyun.com/document_detail/32015.html?spm=a2c4g.11186623.6.683.FM8JCG
   *
   * @param maxKeys (限定返回文件的最大个数,默认返回100个)
   */
  public static List<String> listFiles(String keyPrefix, int maxKeys) {
    // 列举Object。
    ObjectListing objectListing = AliOSSConstants.ossClient
        .listObjects(new ListObjectsRequest(bucketName).withPrefix(keyPrefix).withMaxKeys(maxKeys));
    List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
    List<String> files = new ArrayList<>();
    sums.stream().forEach(s -> files.add(s.getKey()));
    return files;
  }

  /**
   * 分页列举指定个数的文件
   *
   * https://help.aliyun.com/document_detail/32015.html?spm=a2c4g.11186623.6.683.FM8JCG
   *
   * @param
   */
  public static List<String> listFiles(String keyPrefix,String nextMarker ) {
    // 每页默认大小
    final int maxKeys = 100;
    ObjectListing objectListing;
    List<String> files = new ArrayList<>();
    do {
      objectListing = AliOSSConstants.ossClient.listObjects(new ListObjectsRequest(bucketName)
          .withPrefix(keyPrefix).withMarker(nextMarker).withMaxKeys(maxKeys));
      List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
      sums.stream().forEach(s -> files.add(s.getKey()));
      nextMarker = objectListing.getNextMarker();
    } while (objectListing.isTruncated());
    return files;
  }
}
