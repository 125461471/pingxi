package com.heibaba.fupan.entity.rdb;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.heibaba.auditor.BaseEntity;

@Entity
@Table(name="stock_base_info")
@JsonIgnoreProperties(value={"jiluDate"})
public class StockBaseInfoEntity extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Size(min=6, max=6)
	@Column(name = "daima")
	private String daima;
	
	@NotNull
	@Column(name = "zhongwenming")
	private String zhongwenming;
	
	@NotNull
	@Size(min=2, max=4)
	@Column(name = "pinyin")
	private String pinyin;
	
	@NotNull
	@Column(name = "rongzi")
	@Min(0)
	@Max(1)
	private Integer rongzi;
	
	@NotNull
	@Column(name = "quyu")
	private String quyu;
	
	@NotNull
	@Column(name = "bankuai")
	private String bankuai;
	
	@OneToMany//(cascade={CascadeType.MERGE,CascadeType.REFRESH},fetch=FetchType.EAGER,optional=false)
	@JoinColumn(name="daima")
	@OrderBy(value="fashengDate desc, jiluDate desc")
	private List<StockExtInfoEntity> extInfoList;
	
	@OneToMany//(cascade={CascadeType.MERGE,CascadeType.REFRESH},fetch=FetchType.EAGER,optional=false)
	@JoinColumn(name="daima")
	@OrderBy(value="fupaiDate desc")
	private List<StockTfpInfoEntity> tfpInfoList;
	
	@OneToMany//(cascade={CascadeType.MERGE,CascadeType.REFRESH},fetch=FetchType.EAGER,optional=false)
	@JoinColumn(name="daima")
	@OrderBy(value="tixingDate asc")
	private List<StockTxInfoEntity> txInfoList;
	
	//多音字取多个值，不符合我的需求。
//    @PrePersist
//    public void prePersist() {
//        this.pinyin = Pinyin4jUtil.converterToFirstSpell(this.zhongwenming).toUpperCase();
//    }
//      
//    @PreUpdate
//    public void preUpdate() {
//    	this.pinyin = Pinyin4jUtil.converterToFirstSpell(this.zhongwenming).toUpperCase();
//    }
	
	public String getDaima() {
		return daima;
	}
	public void setDaima(String daima) {
		this.daima = daima;
	}
	public String getZhongwenming() {
		return zhongwenming;
	}
	public void setZhongwenming(String zhongwenming) {
		this.zhongwenming = zhongwenming;
	}
	public String getPinyin() {
		return pinyin;
	}
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	public String getQuyu() {
		return quyu;
	}
	public void setQuyu(String quyu) {
		this.quyu = quyu;
	}
	public String getBankuai() {
		return bankuai;
	}
	public void setBankuai(String bankuai) {
		this.bankuai = bankuai;
	}
	public List<StockExtInfoEntity> getExtInfoList() {
		return extInfoList;
	}
	public void setExtInfoList(List<StockExtInfoEntity> extInfoList) {
		this.extInfoList = extInfoList;
	}
	public List<StockTfpInfoEntity> getTfpInfoList() {
		return tfpInfoList;
	}
	public void setTfpInfoList(List<StockTfpInfoEntity> tfpInfoList) {
		this.tfpInfoList = tfpInfoList;
	}

	public Integer getRongzi() {
		return rongzi;
	}

	public void setRongzi(Integer rongzi) {
		this.rongzi = rongzi;
	}

	public List<StockTxInfoEntity> getTxInfoList() {
		return txInfoList;
	}

	public void setTxInfoList(List<StockTxInfoEntity> txInfoList) {
		this.txInfoList = txInfoList;
	}
	
}