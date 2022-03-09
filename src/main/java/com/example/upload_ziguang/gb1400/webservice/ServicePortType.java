/**
 * ServicePortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.example.upload_ziguang.gb1400.webservice;

public interface ServicePortType extends java.rmi.Remote {

    /**
     * Service definition of function ns__SendVehiclePass
     */
    public String sendVehiclePass(String vehiclePassInfo, byte[] platePicData, byte[] carPic1, byte[] carPic2, byte[] carPic3) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__SendAlarmPass
     */
    public String sendAlarmPass(String vehicleAlarmInfo, byte[] platePicData, byte[] carPic1, byte[] carPic2, byte[] carPic3) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__SendDeviceState
     */
    public String sendDeviceState(String deviceState) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__SynTime
     */
    public String synTime() throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__SendVehiclePassEx
     */
    public String sendVehiclePassEx(String vehiclePassInfo) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__SendAlarmPassEx
     */
    public String sendAlarmPassEx(String vehicleAlarmInfo) throws java.rmi.RemoteException;
}
