// GYROSCOPE
    

function initGyroscope(gyroHandler, errorHandler) {

    if (window.DeviceOrientationEvent) {
        // Listen for the deviceorientation event and handle the raw data
        window.addEventListener('deviceorientation', 
            function(eventData) {
                // gamma is the left-to-right tilt in degrees, where right is positive
                gyroscopeTiltLR = eventData.gamma;
                // beta is the front-to-back tilt in degrees, where front is positive
                gyroscopeTiltFB = eventData.beta;
                // alpha is the compass direction the device is facing in degrees
                gyroscopeDirection = eventData.alpha              
                // call our orientation event handler
                gyroHandler(gyroscopeTiltLR, gyroscopeTiltFB, gyroscopeDirection);
            }, true);
  } else {
    errorHandler("gyroscope not supported");
  }
}