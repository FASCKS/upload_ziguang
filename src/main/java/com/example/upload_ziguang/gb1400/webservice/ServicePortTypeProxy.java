package com.example.upload_ziguang.gb1400.webservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServicePortTypeProxy implements ServicePortType {
  Logger logger = LoggerFactory.getLogger(ServicePortTypeProxy.class);
  private String _endpoint = null;
  private ServicePortType servicePortType = null;
  
  public ServicePortTypeProxy() {
    _initServicePortTypeProxy();
  }
  
  public ServicePortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initServicePortTypeProxy();
  }
  
  private void _initServicePortTypeProxy() {
    try {
      servicePortType = (new ServiceLocator()).getService();
      if (servicePortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)servicePortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)servicePortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (servicePortType != null)
      ((javax.xml.rpc.Stub)servicePortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public ServicePortType getServicePortType() {
    if (servicePortType == null)
      _initServicePortTypeProxy();
    return servicePortType;
  }
  
  public String sendVehiclePass(String vehiclePassInfo, byte[] platePicData, byte[] carPic1, byte[] carPic2, byte[] carPic3) throws java.rmi.RemoteException{
    logger.info(carPic1+","+carPic2+"+"+carPic3);
    if (servicePortType == null)
      _initServicePortTypeProxy();
    return servicePortType.sendVehiclePass(vehiclePassInfo, platePicData, carPic1, carPic2, carPic3);
  }

  public String sendAlarmPass(String vehicleAlarmInfo, byte[] platePicData, byte[] carPic1, byte[] carPic2, byte[] carPic3) throws java.rmi.RemoteException{
    if (servicePortType == null)
      _initServicePortTypeProxy();
    return servicePortType.sendAlarmPass(vehicleAlarmInfo, platePicData, carPic1, carPic2, carPic3);
  }

  public String sendDeviceState(String deviceState) throws java.rmi.RemoteException{
    if (servicePortType == null)
      _initServicePortTypeProxy();
    return servicePortType.sendDeviceState(deviceState);
  }

  public String synTime() throws java.rmi.RemoteException{
    if (servicePortType == null)
      _initServicePortTypeProxy();
    return servicePortType.synTime();
  }

  public String sendVehiclePassEx(String vehiclePassInfo) throws java.rmi.RemoteException{
    if (servicePortType == null)
      _initServicePortTypeProxy();
    return servicePortType.sendVehiclePassEx(vehiclePassInfo);
  }

  public String sendAlarmPassEx(String vehicleAlarmInfo) throws java.rmi.RemoteException{
    if (servicePortType == null)
      _initServicePortTypeProxy();
    return servicePortType.sendAlarmPassEx(vehicleAlarmInfo);
  }
  
  
}