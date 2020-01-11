import cv2
from networktables import NetworkTables
import time
# from .grip import GripPipeline
import logging

class NetworkTablePush:

    def __init__(self):
        logging.basicConfig(level=logging.DEBUG)
        print('Initializing NetworkTables')
        #NetworkTables.setClientMode()
        #NetworkTables.setIPAddress('10.60.9.2')
        NetworkTables.initialize(server="10.60.9.2")
        
        # Publish to the '/vision/red_areas' network table
        self.table = NetworkTables.getTable("visionTable")

    def extra_processing(self, xValue):
        """center_x_positions = []
        center_y_positions = []
        widths = []
        heights = []
        
        # Find the bounding boxes of the contours to get x, y, width, and height
        for contour in pipeline.filter_contours_output:
            x, y, w, h = cv2.boundingRect(contour)
            center_x_positions.append(x + w / 2)  # X and Y are coordinates of the top-left corner of the bounding box
            center_y_positions.append(y + h / 2)
            widths.append(w)
            heights.append(h)"""

        self.table.putNumber("xEntry", xValue)

        """table.putNumberArray('y', center_y_positions)
        table.putNumberArray('width', widths)
        table.putNumberArray('height', heights)"""


hello = NetworkTablePush()
time.sleep(1)
hello.extra_processing(456)
