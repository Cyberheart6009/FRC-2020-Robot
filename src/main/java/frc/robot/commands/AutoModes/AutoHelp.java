/** Each of the auto modes serve a different purpose.
 * AutoTop___ = if the bot position is on the top of the field (from the position red trench run on top, blue trench run on bottom)
 * AutoMiddle___ = if the bot position is in the middle of the field
 * AutoBottom___ = if the bot position is on the bottom of the field from the aforementioned perspective
 * S at the end of bot commands stands for shoot. 
 * P at the end of the bot commands stands for pickup (picking up balls)
 * 
 * Here are how many balls each auto mode shoots:
 * 
 * AutoTopS: 3
 * AutoTopSPS: 6
 * AutoTopPSPS: 8
 * 
 * AutoMiddleS: 3
 * AutoMiddleSPS: 6
 * AutoMiddlePSPS: 8
 * 
 * AutoBottomS: 3
 * AutoBottomSPS: 6
 * AutoBottomPSPS: 8
 * 
 * All balls are being shot 150 inches from the hole. In some instances, the program starts 175 inches from start just so that
 * my previous calculations (which were calculated with the presumption that 175" was better for shooting) still applies.
 * 
 * TODO:
 * Replace all instances of followtarget with a sequential command group that includes pidturn and drivedistance
 */