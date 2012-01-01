package ic.doc.cpp.client.admin;

import com.smartgwt.client.widgets.tree.TreeNode;

public class CategoryTreeNode extends TreeNode{
	
    public CategoryTreeNode(Long categoryId, String categoryName, Long parentId) {  
        setCategoryId(categoryId);  
        setCategoryName(categoryName);  
        setParentId(parentId);  
    }  

    private void setParentId(Long parentId) {
    	 setAttribute("parentId", parentId);  
	}

	private void setCategoryName(String categoryName) {
		 setAttribute("categoryName", categoryName);  
	}

	private void setCategoryId(Long categoryId) {
		 setAttribute("categoryId", categoryId);  	
	}


    
}
