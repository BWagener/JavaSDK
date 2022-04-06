package com.playfab;

import java.util.*;
import com.playfab.PlayFabUtil.*;

public class PlayFabGroupsModels {

    /**
     * Accepts an outstanding invitation to to join a group if the invited entity is not blocked by the group. Nothing is
     * returned in the case of success.
     */
    public static class AcceptGroupApplicationRequest {
        /** The optional custom tags associated with the request (e.g. build number, external trace identifiers, etc.). */
        public Map<String,String> CustomTags;
        /**
         * Type of the entity to accept as. Must be the same entity as the claimant or an entity that is a child of the claimant
         * entity.
         */
        public EntityKey Entity;
        /** The identifier of the group */
        public EntityKey Group;
        
    }

    /**
     * Accepts an outstanding invitation to join the group if the invited entity is not blocked by the group. Only the invited
     * entity or a parent in its chain (e.g. title) may accept the invitation on the invited entity's behalf. Nothing is
     * returned in the case of success.
     */
    public static class AcceptGroupInvitationRequest {
        /** The optional custom tags associated with the request (e.g. build number, external trace identifiers, etc.). */
        public Map<String,String> CustomTags;
        /** The optional entity to perform this action on. Defaults to the currently logged in entity. */
        public EntityKey Entity;
        /** The identifier of the group */
        public EntityKey Group;
        
    }

    /**
     * Adds members to a group or role. Existing members of the group will added to roles within the group, but if the user is
     * not already a member of the group, only title claimants may add them to the group, and others must use the group
     * application or invite system to add new members to a group. Returns nothing if successful.
     */
    public static class AddMembersRequest {
        /** The optional custom tags associated with the request (e.g. build number, external trace identifiers, etc.). */
        public Map<String,String> CustomTags;
        /** The identifier of the group */
        public EntityKey Group;
        /** List of entities to add to the group. Only entities of type title_player_account and character may be added to groups. */
        public ArrayList<EntityKey> Members;
        /**
         * Optional: The ID of the existing role to add the entities to. If this is not specified, the default member role for the
         * group will be used. Role IDs must be between 1 and 64 characters long.
         */
        public String RoleId;
        
    }

    /**
     * Creates an application to join a group. Calling this while a group application already exists will return the same
     * application instead of an error and will not refresh the time before the application expires. By default, if the entity
     * has an invitation to join the group outstanding, this will accept the invitation to join the group instead and return an
     * error indicating such, rather than creating a duplicate application to join that will need to be cleaned up later.
     * Returns information about the application or an error indicating an invitation was accepted instead.
     */
    public static class ApplyToGroupRequest {
        /** Optional, default true. Automatically accept an outstanding invitation if one exists instead of creating an application */
        public Boolean AutoAcceptOutstandingInvite;
        /** The optional custom tags associated with the request (e.g. build number, external trace identifiers, etc.). */
        public Map<String,String> CustomTags;
        /** The optional entity to perform this action on. Defaults to the currently logged in entity. */
        public EntityKey Entity;
        /** The identifier of the group */
        public EntityKey Group;
        
    }

    /** Describes an application to join a group */
    public static class ApplyToGroupResponse {
        /** Type of entity that requested membership */
        public EntityWithLineage Entity;
        /** When the application to join will expire and be deleted */
        public Date Expires;
        /** ID of the group that the entity requesting membership to */
        public EntityKey Group;
        
    }

    /**
     * Blocks a list of entities from joining a group. Blocked entities may not create new applications to join, be invited to
     * join, accept an invitation, or have an application accepted. Failure due to being blocked does not clean up existing
     * applications or invitations to the group. No data is returned in the case of success.
     */
    public static class BlockEntityRequest {
        /** The optional custom tags associated with the request (e.g. build number, external trace identifiers, etc.). */
        public Map<String,String> CustomTags;
        /** The entity to perform this action on. */
        public EntityKey Entity;
        /** The identifier of the group */
        public EntityKey Group;
        
    }

    /**
     * Changes the role membership of a list of entities from one role to another in in a single operation. The destination
     * role must already exist. This is equivalent to adding the entities to the destination role and removing from the origin
     * role. Returns nothing if successful.
     */
    public static class ChangeMemberRoleRequest {
        /** The optional custom tags associated with the request (e.g. build number, external trace identifiers, etc.). */
        public Map<String,String> CustomTags;
        /**
         * The ID of the role that the entities will become a member of. This must be an existing role. Role IDs must be between 1
         * and 64 characters long.
         */
        public String DestinationRoleId;
        /** The identifier of the group */
        public EntityKey Group;
        /**
         * List of entities to move between roles in the group. All entities in this list must be members of the group and origin
         * role.
         */
        public ArrayList<EntityKey> Members;
        /** The ID of the role that the entities currently are a member of. Role IDs must be between 1 and 64 characters long. */
        public String OriginRoleId;
        
    }

    /**
     * Creates a new group, as well as administration and member roles, based off of a title's group template. Returns
     * information about the group that was created.
     */
    public static class CreateGroupRequest {
        /** The optional custom tags associated with the request (e.g. build number, external trace identifiers, etc.). */
        public Map<String,String> CustomTags;
        /** The optional entity to perform this action on. Defaults to the currently logged in entity. */
        public EntityKey Entity;
        /** The name of the group. This is unique at the title level by default. */
        public String GroupName;
        
    }

    public static class CreateGroupResponse {
        /** The ID of the administrator role for the group. */
        public String AdminRoleId;
        /** The server date and time the group was created. */
        public Date Created;
        /** The identifier of the group */
        public EntityKey Group;
        /** The name of the group. */
        public String GroupName;
        /** The ID of the default member role for the group. */
        public String MemberRoleId;
        /** The current version of the profile, can be used for concurrency control during updates. */
        public Integer ProfileVersion;
        /** The list of roles and names that belong to the group. */
        public Map<String,String> Roles;
        
    }

    /**
     * Creates a new role within an existing group, with no members. Both the role ID and role name must be unique within the
     * group, but the name can be the same as the ID. The role ID is set at creation and cannot be changed. Returns information
     * about the role that was created.
     */
    public static class CreateGroupRoleRequest {
        /** The optional custom tags associated with the request (e.g. build number, external trace identifiers, etc.). */
        public Map<String,String> CustomTags;
        /** The identifier of the group */
        public EntityKey Group;
        /**
         * The ID of the role. This must be unique within the group and cannot be changed. Role IDs must be between 1 and 64
         * characters long.
         */
        public String RoleId;
        /**
         * The name of the role. This must be unique within the group and can be changed later. Role names must be between 1 and
         * 100 characters long
         */
        public String RoleName;
        
    }

    public static class CreateGroupRoleResponse {
        /** The current version of the group profile, can be used for concurrency control during updates. */
        public Integer ProfileVersion;
        /** ID for the role */
        public String RoleId;
        /** The name of the role */
        public String RoleName;
        
    }

    /**
     * Deletes a group and all roles, invitations, join requests, and blocks associated with it. Permission to delete is only
     * required the group itself to execute this action. The group and data cannot be cannot be recovered once removed, but any
     * abuse reports about the group will remain. No data is returned in the case of success.
     */
    public static class DeleteGroupRequest {
        /** The optional custom tags associated with the request (e.g. build number, external trace identifiers, etc.). */
        public Map<String,String> CustomTags;
        /** ID of the group or role to remove */
        public EntityKey Group;
        
    }

    /** Returns information about the role */
    public static class DeleteRoleRequest {
        /** The optional custom tags associated with the request (e.g. build number, external trace identifiers, etc.). */
        public Map<String,String> CustomTags;
        /** The identifier of the group */
        public EntityKey Group;
        /** The ID of the role to delete. Role IDs must be between 1 and 64 characters long. */
        public String RoleId;
        
    }

    public static class EmptyResponse {
        
    }

    /** Combined entity type and ID structure which uniquely identifies a single entity. */
    public static class EntityKey {
        /** Unique ID of the entity. */
        public String Id;
        /** Entity type. See https://docs.microsoft.com/gaming/playfab/features/data/entities/available-built-in-entity-types */
        public String Type;
        
    }

    public static class EntityMemberRole {
        /** The list of members in the role */
        public ArrayList<EntityWithLineage> Members;
        /** The ID of the role. */
        public String RoleId;
        /** The name of the role */
        public String RoleName;
        
    }

    /** Entity wrapper class that contains the entity key and the entities that make up the lineage of the entity. */
    public static class EntityWithLineage {
        /** The entity key for the specified entity */
        public EntityKey Key;
        /** Dictionary of entity keys for related entities. Dictionary key is entity type. */
        public Map<String,EntityKey> Lineage;
        
    }

    /** Returns the ID, name, role list and other non-membership related information about a group. */
    public static class GetGroupRequest {
        /** The optional custom tags associated with the request (e.g. build number, external trace identifiers, etc.). */
        public Map<String,String> CustomTags;
        /** The identifier of the group */
        public EntityKey Group;
        /** The full name of the group */
        public String GroupName;
        
    }

    public static class GetGroupResponse {
        /** The ID of the administrator role for the group. */
        public String AdminRoleId;
        /** The server date and time the group was created. */
        public Date Created;
        /** The identifier of the group */
        public EntityKey Group;
        /** The name of the group. */
        public String GroupName;
        /** The ID of the default member role for the group. */
        public String MemberRoleId;
        /** The current version of the profile, can be used for concurrency control during updates. */
        public Integer ProfileVersion;
        /** The list of roles and names that belong to the group. */
        public Map<String,String> Roles;
        
    }

    /** Describes an application to join a group */
    public static class GroupApplication {
        /** Type of entity that requested membership */
        public EntityWithLineage Entity;
        /** When the application to join will expire and be deleted */
        public Date Expires;
        /** ID of the group that the entity requesting membership to */
        public EntityKey Group;
        
    }

    /** Describes an entity that is blocked from joining a group. */
    public static class GroupBlock {
        /** The entity that is blocked */
        public EntityWithLineage Entity;
        /** ID of the group that the entity is blocked from */
        public EntityKey Group;
        
    }

    /** Describes an invitation to a group. */
    public static class GroupInvitation {
        /** When the invitation will expire and be deleted */
        public Date Expires;
        /** The group that the entity invited to */
        public EntityKey Group;
        /** The entity that created the invitation */
        public EntityWithLineage InvitedByEntity;
        /** The entity that is invited */
        public EntityWithLineage InvitedEntity;
        /** ID of the role in the group to assign the user to. */
        public String RoleId;
        
    }

    /** Describes a group role */
    public static class GroupRole {
        /** ID for the role */
        public String RoleId;
        /** The name of the role */
        public String RoleName;
        
    }

    /** Describes a group and the roles that it contains */
    public static class GroupWithRoles {
        /** ID for the group */
        public EntityKey Group;
        /** The name of the group */
        public String GroupName;
        /** The current version of the profile, can be used for concurrency control during updates. */
        public Integer ProfileVersion;
        /** The list of roles within the group */
        public ArrayList<GroupRole> Roles;
        
    }

    /**
     * Invites a player to join a group, if they are not blocked by the group. An optional role can be provided to
     * automatically assign the player to the role if they accept the invitation. By default, if the entity has an application
     * to the group outstanding, this will accept the application instead and return an error indicating such, rather than
     * creating a duplicate invitation to join that will need to be cleaned up later. Returns information about the new
     * invitation or an error indicating an existing application to join was accepted.
     */
    public static class InviteToGroupRequest {
        /** Optional, default true. Automatically accept an application if one exists instead of creating an invitation */
        public Boolean AutoAcceptOutstandingApplication;
        /** The optional custom tags associated with the request (e.g. build number, external trace identifiers, etc.). */
        public Map<String,String> CustomTags;
        /** The entity to perform this action on. */
        public EntityKey Entity;
        /** The identifier of the group */
        public EntityKey Group;
        /**
         * Optional. ID of an existing a role in the group to assign the user to. The group's default member role is used if this
         * is not specified. Role IDs must be between 1 and 64 characters long.
         */
        public String RoleId;
        
    }

    /** Describes an invitation to a group. */
    public static class InviteToGroupResponse {
        /** When the invitation will expire and be deleted */
        public Date Expires;
        /** The group that the entity invited to */
        public EntityKey Group;
        /** The entity that created the invitation */
        public EntityWithLineage InvitedByEntity;
        /** The entity that is invited */
        public EntityWithLineage InvitedEntity;
        /** ID of the role in the group to assign the user to. */
        public String RoleId;
        
    }

    /**
     * Checks to see if an entity is a member of a group or role within the group. A result indicating if the entity is a
     * member of the group is returned, or a permission error if the caller does not have permission to read the group's member
     * list.
     */
    public static class IsMemberRequest {
        /** The optional custom tags associated with the request (e.g. build number, external trace identifiers, etc.). */
        public Map<String,String> CustomTags;
        /** The entity to perform this action on. */
        public EntityKey Entity;
        /** The identifier of the group */
        public EntityKey Group;
        /**
         * Optional: ID of the role to check membership of. Defaults to any role (that is, check to see if the entity is a member
         * of the group in any capacity) if not specified.
         */
        public String RoleId;
        
    }

    public static class IsMemberResponse {
        /** A value indicating whether or not the entity is a member. */
        public Boolean IsMember;
        
    }

    /**
     * Lists all outstanding requests to join a group. Returns a list of all requests to join, as well as when the request will
     * expire. To get the group applications for a specific entity, use ListMembershipOpportunities.
     */
    public static class ListGroupApplicationsRequest {
        /** The optional custom tags associated with the request (e.g. build number, external trace identifiers, etc.). */
        public Map<String,String> CustomTags;
        /** The identifier of the group */
        public EntityKey Group;
        
    }

    public static class ListGroupApplicationsResponse {
        /** The requested list of applications to the group. */
        public ArrayList<GroupApplication> Applications;
        
    }

    /** Lists all entities blocked from joining a group. A list of blocked entities is returned */
    public static class ListGroupBlocksRequest {
        /** The optional custom tags associated with the request (e.g. build number, external trace identifiers, etc.). */
        public Map<String,String> CustomTags;
        /** The identifier of the group */
        public EntityKey Group;
        
    }

    public static class ListGroupBlocksResponse {
        /** The requested list blocked entities. */
        public ArrayList<GroupBlock> BlockedEntities;
        
    }

    /**
     * Lists all outstanding invitations for a group. Returns a list of entities that have been invited, as well as when the
     * invitation will expire. To get the group invitations for a specific entity, use ListMembershipOpportunities.
     */
    public static class ListGroupInvitationsRequest {
        /** The optional custom tags associated with the request (e.g. build number, external trace identifiers, etc.). */
        public Map<String,String> CustomTags;
        /** The identifier of the group */
        public EntityKey Group;
        
    }

    public static class ListGroupInvitationsResponse {
        /** The requested list of group invitations. */
        public ArrayList<GroupInvitation> Invitations;
        
    }

    /**
     * Gets a list of members and the roles they belong to within the group. If the caller does not have permission to view the
     * role, and the member is in no other role, the member is not displayed. Returns a list of entities that are members of
     * the group.
     */
    public static class ListGroupMembersRequest {
        /** The optional custom tags associated with the request (e.g. build number, external trace identifiers, etc.). */
        public Map<String,String> CustomTags;
        /** ID of the group to list the members and roles for */
        public EntityKey Group;
        
    }

    public static class ListGroupMembersResponse {
        /** The requested list of roles and member entity IDs. */
        public ArrayList<EntityMemberRole> Members;
        
    }

    /**
     * Lists all outstanding group applications and invitations for an entity. Anyone may call this for any entity, but data
     * will only be returned for the entity or a parent of that entity. To list invitations or applications for a group to
     * check if a player is trying to join, use ListGroupInvitations and ListGroupApplications.
     */
    public static class ListMembershipOpportunitiesRequest {
        /** The optional custom tags associated with the request (e.g. build number, external trace identifiers, etc.). */
        public Map<String,String> CustomTags;
        /** The optional entity to perform this action on. Defaults to the currently logged in entity. */
        public EntityKey Entity;
        
    }

    public static class ListMembershipOpportunitiesResponse {
        /** The requested list of group applications. */
        public ArrayList<GroupApplication> Applications;
        /** The requested list of group invitations. */
        public ArrayList<GroupInvitation> Invitations;
        
    }

    /**
     * Lists the groups and roles that an entity is a part of, checking to see if group and role metadata and memberships
     * should be visible to the caller. If the entity is not in any roles that are visible to the caller, the group is not
     * returned in the results, even if the caller otherwise has permission to see that the entity is a member of that group.
     */
    public static class ListMembershipRequest {
        /** The optional custom tags associated with the request (e.g. build number, external trace identifiers, etc.). */
        public Map<String,String> CustomTags;
        /** The optional entity to perform this action on. Defaults to the currently logged in entity. */
        public EntityKey Entity;
        
    }

    public static class ListMembershipResponse {
        /** The list of groups */
        public ArrayList<GroupWithRoles> Groups;
        
    }

    public static enum OperationTypes {
        Created,
        Updated,
        Deleted,
        None
    }

    /**
     * Removes an existing application to join the group. This is used for both rejection of an application as well as
     * withdrawing an application. The applying entity or a parent in its chain (e.g. title) may withdraw the application, and
     * any caller with appropriate access in the group may reject an application. No data is returned in the case of success.
     */
    public static class RemoveGroupApplicationRequest {
        /** The optional custom tags associated with the request (e.g. build number, external trace identifiers, etc.). */
        public Map<String,String> CustomTags;
        /** The entity to perform this action on. */
        public EntityKey Entity;
        /** The identifier of the group */
        public EntityKey Group;
        
    }

    /**
     * Removes an existing invitation to join the group. This is used for both rejection of an invitation as well as rescinding
     * an invitation. The invited entity or a parent in its chain (e.g. title) may reject the invitation by calling this
     * method, and any caller with appropriate access in the group may rescind an invitation. No data is returned in the case
     * of success.
     */
    public static class RemoveGroupInvitationRequest {
        /** The optional custom tags associated with the request (e.g. build number, external trace identifiers, etc.). */
        public Map<String,String> CustomTags;
        /** The entity to perform this action on. */
        public EntityKey Entity;
        /** The identifier of the group */
        public EntityKey Group;
        
    }

    /**
     * Removes members from a group. A member can always remove themselves from a group, regardless of permissions. Returns
     * nothing if successful.
     */
    public static class RemoveMembersRequest {
        /** The optional custom tags associated with the request (e.g. build number, external trace identifiers, etc.). */
        public Map<String,String> CustomTags;
        /** The identifier of the group */
        public EntityKey Group;
        /** List of entities to remove */
        public ArrayList<EntityKey> Members;
        /** The ID of the role to remove the entities from. */
        public String RoleId;
        
    }

    /** Unblocks a list of entities from joining a group. No data is returned in the case of success. */
    public static class UnblockEntityRequest {
        /** The optional custom tags associated with the request (e.g. build number, external trace identifiers, etc.). */
        public Map<String,String> CustomTags;
        /** The entity to perform this action on. */
        public EntityKey Entity;
        /** The identifier of the group */
        public EntityKey Group;
        
    }

    /**
     * Updates data about a group, such as the name or default member role. Returns information about whether the update was
     * successful. Only title claimants may modify the administration role for a group.
     */
    public static class UpdateGroupRequest {
        /** Optional: the ID of an existing role to set as the new administrator role for the group */
        public String AdminRoleId;
        /** The optional custom tags associated with the request (e.g. build number, external trace identifiers, etc.). */
        public Map<String,String> CustomTags;
        /**
         * Optional field used for concurrency control. By specifying the previously returned value of ProfileVersion from the
         * GetGroup API, you can ensure that the group data update will only be performed if the group has not been updated by any
         * other clients since the version you last loaded.
         */
        public Integer ExpectedProfileVersion;
        /** The identifier of the group */
        public EntityKey Group;
        /** Optional: the new name of the group */
        public String GroupName;
        /** Optional: the ID of an existing role to set as the new member role for the group */
        public String MemberRoleId;
        
    }

    public static class UpdateGroupResponse {
        /** Optional reason to explain why the operation was the result that it was. */
        public String OperationReason;
        /** New version of the group data. */
        public Integer ProfileVersion;
        /** Indicates which operation was completed, either Created, Updated, Deleted or None. */
        public OperationTypes SetResult;
        
    }

    /** Updates the role name. Returns information about whether the update was successful. */
    public static class UpdateGroupRoleRequest {
        /** The optional custom tags associated with the request (e.g. build number, external trace identifiers, etc.). */
        public Map<String,String> CustomTags;
        /**
         * Optional field used for concurrency control. By specifying the previously returned value of ProfileVersion from the
         * GetGroup API, you can ensure that the group data update will only be performed if the group has not been updated by any
         * other clients since the version you last loaded.
         */
        public Integer ExpectedProfileVersion;
        /** The identifier of the group */
        public EntityKey Group;
        /** ID of the role to update. Role IDs must be between 1 and 64 characters long. */
        public String RoleId;
        /** The new name of the role */
        public String RoleName;
        
    }

    public static class UpdateGroupRoleResponse {
        /** Optional reason to explain why the operation was the result that it was. */
        public String OperationReason;
        /** New version of the role data. */
        public Integer ProfileVersion;
        /** Indicates which operation was completed, either Created, Updated, Deleted or None. */
        public OperationTypes SetResult;
        
    }

}
