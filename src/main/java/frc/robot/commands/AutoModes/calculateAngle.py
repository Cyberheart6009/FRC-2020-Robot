import math

px_inch = 1
px_deg = 1
#placeholder value for camera centre pixel
camera_centre_pixel = 0
ball_centre_pixel = 0

def getsize(xVal1, xVal2):
    global ball_centre_pixel
    xDifference = xVal2 - xVal1
    size = xDifference / px_inch
    ball_centre_pixel = xVal1 + (size/2)
    return size

def getAngle(xVal1, xVal2):
    xDifference = xVal2 - xVal1
    angle = xDifference / px_deg
    return angle

def getDistance(size, angle):
    #tan(angle) = opp/adj
    #adj = opp / tan(angle)
    distance = size/2 /math.tan(angle/2) 

def ifTurn(camera_centre_pixel, ball_centre_pixel):
    global ball_centre_pixel
    if camera_centre_pixel < ball_centre_pixel:
        #turn right
        return 1
    elif camera_centre_pixel > ball_centre_pixel:
        #turn left
        return 0
    else:
        #you don't need to turn
        return 2
