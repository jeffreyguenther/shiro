package shiro;

/**
 * Methods to help with creating names, and fully qualified names
 * @author jeffreyguenther
 */
public class PathHelpers {
    /**
     * Extracts the name of a port from a full path name.
     * Given a full name like "Area.length", the results is "length"
     * @param fullName fully qualified name of the port
     * @return the name of the port
     */
    public static String getNameFromPath(String fullName){
        // update name
        int lastDotIndex = fullName.lastIndexOf(".");
        if( lastDotIndex != -1){
            return fullName.substring(lastDotIndex + 1, fullName.length());
        }else{
            return fullName;
        }
    }
    
    /**
     * Replaces the name in a full name
     * If you want to change Area.update to Area.calc
     * pass fullName = "Area.update", and name ="calc"
     * @param fullName old fully qualified name
     * @param name new name
     * @return the new fully qualified name
     */
    public static String replaceNameInPath(String fullName, String name){
        
        // Check if the fullName is a path.
        if(fullName.contains(".")){
            int prefixIndex = fullName.lastIndexOf(".");
            String prefix = fullName.substring(0, prefixIndex);
            return prefix + "." + name;
        }else{
            return name;
        }
    }
    
    /***
     * Create the node's full name
     * @param parentFullName the node's parent's full name
     * @param nodeName name of the node
     * @return the node's full name
     */
    public static String createFullName(String parentFullName, String nodeName) {
        if("".equals(parentFullName)){
           return nodeName;
        }else{
            return parentFullName + "." + nodeName;
        }
    }
}
