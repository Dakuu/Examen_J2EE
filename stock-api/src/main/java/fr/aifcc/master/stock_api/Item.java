package fr.aifcc.master.stock_api;

public abstract class Item
{
	private long id;
	
	//Accesseur de id
	public long getId()
	{
		return this.id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
}
