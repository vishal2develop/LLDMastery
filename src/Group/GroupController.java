package Group;

import User.User;

import java.util.ArrayList;
import java.util.List;

public class GroupController {
    List<Group> groupList;

    public GroupController(){
        this.groupList = new ArrayList<>();
    }

    public void createNewGroup(String groupId, String groupName, User createdByUser){
        // Create  a new  group and add to groupList
        Group newGroup = new Group();
        newGroup.setGroupId(groupId);
        newGroup.setGroupName(groupName);

        // add the user who created the group into the group
        newGroup.addMember(createdByUser);

        // add the group in groupList
        groupList.add(newGroup);
    }

    public Group getGroup(String groupId){
        for (Group group: groupList){
            if(group.getGroupId().equals(groupId)){
                return group;
            }
        }
        return null;
    }
}
