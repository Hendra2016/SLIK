package com.bank.tree;

import java.util.List;

public abstract class RODTreeNodeData {
	/**
	* get children of this data
	* @return
	*/
	public abstract List<? extends RODTreeNodeData> getChildren();
	/**
	* get child count of this data, do not need to really get children
	* @return
	*/
	public abstract int getChildCount ();
}
