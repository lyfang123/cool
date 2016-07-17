package com.uwaytech.separate.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SeparateExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SeparateExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andSupplierIdIsNull() {
            addCriterion("supplier_id is null");
            return (Criteria) this;
        }

        public Criteria andSupplierIdIsNotNull() {
            addCriterion("supplier_id is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierIdEqualTo(Long value) {
            addCriterion("supplier_id =", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdNotEqualTo(Long value) {
            addCriterion("supplier_id <>", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdGreaterThan(Long value) {
            addCriterion("supplier_id >", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdGreaterThanOrEqualTo(Long value) {
            addCriterion("supplier_id >=", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdLessThan(Long value) {
            addCriterion("supplier_id <", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdLessThanOrEqualTo(Long value) {
            addCriterion("supplier_id <=", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdIn(List<Long> values) {
            addCriterion("supplier_id in", values, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdNotIn(List<Long> values) {
            addCriterion("supplier_id not in", values, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdBetween(Long value1, Long value2) {
            addCriterion("supplier_id between", value1, value2, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdNotBetween(Long value1, Long value2) {
            addCriterion("supplier_id not between", value1, value2, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSeparateMonthIsNull() {
            addCriterion("separate_month is null");
            return (Criteria) this;
        }

        public Criteria andSeparateMonthIsNotNull() {
            addCriterion("separate_month is not null");
            return (Criteria) this;
        }

        public Criteria andSeparateMonthEqualTo(Date value) {
            addCriterion("separate_month =", value, "separateMonth");
            return (Criteria) this;
        }

        public Criteria andSeparateMonthNotEqualTo(Date value) {
            addCriterion("separate_month <>", value, "separateMonth");
            return (Criteria) this;
        }

        public Criteria andSeparateMonthGreaterThan(Date value) {
            addCriterion("separate_month >", value, "separateMonth");
            return (Criteria) this;
        }

        public Criteria andSeparateMonthGreaterThanOrEqualTo(Date value) {
            addCriterion("separate_month >=", value, "separateMonth");
            return (Criteria) this;
        }

        public Criteria andSeparateMonthLessThan(Date value) {
            addCriterion("separate_month <", value, "separateMonth");
            return (Criteria) this;
        }

        public Criteria andSeparateMonthLessThanOrEqualTo(Date value) {
            addCriterion("separate_month <=", value, "separateMonth");
            return (Criteria) this;
        }

        public Criteria andSeparateMonthIn(List<Date> values) {
            addCriterion("separate_month in", values, "separateMonth");
            return (Criteria) this;
        }

        public Criteria andSeparateMonthNotIn(List<Date> values) {
            addCriterion("separate_month not in", values, "separateMonth");
            return (Criteria) this;
        }

        public Criteria andSeparateMonthBetween(Date value1, Date value2) {
            addCriterion("separate_month between", value1, value2, "separateMonth");
            return (Criteria) this;
        }

        public Criteria andSeparateMonthNotBetween(Date value1, Date value2) {
            addCriterion("separate_month not between", value1, value2, "separateMonth");
            return (Criteria) this;
        }

        public Criteria andDownloadCountIsNull() {
            addCriterion("download_count is null");
            return (Criteria) this;
        }

        public Criteria andDownloadCountIsNotNull() {
            addCriterion("download_count is not null");
            return (Criteria) this;
        }

        public Criteria andDownloadCountEqualTo(Integer value) {
            addCriterion("download_count =", value, "downloadCount");
            return (Criteria) this;
        }

        public Criteria andDownloadCountNotEqualTo(Integer value) {
            addCriterion("download_count <>", value, "downloadCount");
            return (Criteria) this;
        }

        public Criteria andDownloadCountGreaterThan(Integer value) {
            addCriterion("download_count >", value, "downloadCount");
            return (Criteria) this;
        }

        public Criteria andDownloadCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("download_count >=", value, "downloadCount");
            return (Criteria) this;
        }

        public Criteria andDownloadCountLessThan(Integer value) {
            addCriterion("download_count <", value, "downloadCount");
            return (Criteria) this;
        }

        public Criteria andDownloadCountLessThanOrEqualTo(Integer value) {
            addCriterion("download_count <=", value, "downloadCount");
            return (Criteria) this;
        }

        public Criteria andDownloadCountIn(List<Integer> values) {
            addCriterion("download_count in", values, "downloadCount");
            return (Criteria) this;
        }

        public Criteria andDownloadCountNotIn(List<Integer> values) {
            addCriterion("download_count not in", values, "downloadCount");
            return (Criteria) this;
        }

        public Criteria andDownloadCountBetween(Integer value1, Integer value2) {
            addCriterion("download_count between", value1, value2, "downloadCount");
            return (Criteria) this;
        }

        public Criteria andDownloadCountNotBetween(Integer value1, Integer value2) {
            addCriterion("download_count not between", value1, value2, "downloadCount");
            return (Criteria) this;
        }

        public Criteria andRmbAmountIsNull() {
            addCriterion("rmb_amount is null");
            return (Criteria) this;
        }

        public Criteria andRmbAmountIsNotNull() {
            addCriterion("rmb_amount is not null");
            return (Criteria) this;
        }

        public Criteria andRmbAmountEqualTo(Long value) {
            addCriterion("rmb_amount =", value, "rmbAmount");
            return (Criteria) this;
        }

        public Criteria andRmbAmountNotEqualTo(Long value) {
            addCriterion("rmb_amount <>", value, "rmbAmount");
            return (Criteria) this;
        }

        public Criteria andRmbAmountGreaterThan(Long value) {
            addCriterion("rmb_amount >", value, "rmbAmount");
            return (Criteria) this;
        }

        public Criteria andRmbAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("rmb_amount >=", value, "rmbAmount");
            return (Criteria) this;
        }

        public Criteria andRmbAmountLessThan(Long value) {
            addCriterion("rmb_amount <", value, "rmbAmount");
            return (Criteria) this;
        }

        public Criteria andRmbAmountLessThanOrEqualTo(Long value) {
            addCriterion("rmb_amount <=", value, "rmbAmount");
            return (Criteria) this;
        }

        public Criteria andRmbAmountIn(List<Long> values) {
            addCriterion("rmb_amount in", values, "rmbAmount");
            return (Criteria) this;
        }

        public Criteria andRmbAmountNotIn(List<Long> values) {
            addCriterion("rmb_amount not in", values, "rmbAmount");
            return (Criteria) this;
        }

        public Criteria andRmbAmountBetween(Long value1, Long value2) {
            addCriterion("rmb_amount between", value1, value2, "rmbAmount");
            return (Criteria) this;
        }

        public Criteria andRmbAmountNotBetween(Long value1, Long value2) {
            addCriterion("rmb_amount not between", value1, value2, "rmbAmount");
            return (Criteria) this;
        }

        public Criteria andEEarningAmountIsNull() {
            addCriterion("e_earning_amount is null");
            return (Criteria) this;
        }

        public Criteria andEEarningAmountIsNotNull() {
            addCriterion("e_earning_amount is not null");
            return (Criteria) this;
        }

        public Criteria andEEarningAmountEqualTo(Long value) {
            addCriterion("e_earning_amount =", value, "eEarningAmount");
            return (Criteria) this;
        }

        public Criteria andEEarningAmountNotEqualTo(Long value) {
            addCriterion("e_earning_amount <>", value, "eEarningAmount");
            return (Criteria) this;
        }

        public Criteria andEEarningAmountGreaterThan(Long value) {
            addCriterion("e_earning_amount >", value, "eEarningAmount");
            return (Criteria) this;
        }

        public Criteria andEEarningAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("e_earning_amount >=", value, "eEarningAmount");
            return (Criteria) this;
        }

        public Criteria andEEarningAmountLessThan(Long value) {
            addCriterion("e_earning_amount <", value, "eEarningAmount");
            return (Criteria) this;
        }

        public Criteria andEEarningAmountLessThanOrEqualTo(Long value) {
            addCriterion("e_earning_amount <=", value, "eEarningAmount");
            return (Criteria) this;
        }

        public Criteria andEEarningAmountIn(List<Long> values) {
            addCriterion("e_earning_amount in", values, "eEarningAmount");
            return (Criteria) this;
        }

        public Criteria andEEarningAmountNotIn(List<Long> values) {
            addCriterion("e_earning_amount not in", values, "eEarningAmount");
            return (Criteria) this;
        }

        public Criteria andEEarningAmountBetween(Long value1, Long value2) {
            addCriterion("e_earning_amount between", value1, value2, "eEarningAmount");
            return (Criteria) this;
        }

        public Criteria andEEarningAmountNotBetween(Long value1, Long value2) {
            addCriterion("e_earning_amount not between", value1, value2, "eEarningAmount");
            return (Criteria) this;
        }

        public Criteria andProportionIsNull() {
            addCriterion("proportion is null");
            return (Criteria) this;
        }

        public Criteria andProportionIsNotNull() {
            addCriterion("proportion is not null");
            return (Criteria) this;
        }

        public Criteria andProportionEqualTo(Double value) {
            addCriterion("proportion =", value, "proportion");
            return (Criteria) this;
        }

        public Criteria andProportionNotEqualTo(Double value) {
            addCriterion("proportion <>", value, "proportion");
            return (Criteria) this;
        }

        public Criteria andProportionGreaterThan(Double value) {
            addCriterion("proportion >", value, "proportion");
            return (Criteria) this;
        }

        public Criteria andProportionGreaterThanOrEqualTo(Double value) {
            addCriterion("proportion >=", value, "proportion");
            return (Criteria) this;
        }

        public Criteria andProportionLessThan(Double value) {
            addCriterion("proportion <", value, "proportion");
            return (Criteria) this;
        }

        public Criteria andProportionLessThanOrEqualTo(Double value) {
            addCriterion("proportion <=", value, "proportion");
            return (Criteria) this;
        }

        public Criteria andProportionIn(List<Double> values) {
            addCriterion("proportion in", values, "proportion");
            return (Criteria) this;
        }

        public Criteria andProportionNotIn(List<Double> values) {
            addCriterion("proportion not in", values, "proportion");
            return (Criteria) this;
        }

        public Criteria andProportionBetween(Double value1, Double value2) {
            addCriterion("proportion between", value1, value2, "proportion");
            return (Criteria) this;
        }

        public Criteria andProportionNotBetween(Double value1, Double value2) {
            addCriterion("proportion not between", value1, value2, "proportion");
            return (Criteria) this;
        }

        public Criteria andECoinIsNull() {
            addCriterion("e_coin is null");
            return (Criteria) this;
        }

        public Criteria andECoinIsNotNull() {
            addCriterion("e_coin is not null");
            return (Criteria) this;
        }

        public Criteria andECoinEqualTo(Long value) {
            addCriterion("e_coin =", value, "eCoin");
            return (Criteria) this;
        }

        public Criteria andECoinNotEqualTo(Long value) {
            addCriterion("e_coin <>", value, "eCoin");
            return (Criteria) this;
        }

        public Criteria andECoinGreaterThan(Long value) {
            addCriterion("e_coin >", value, "eCoin");
            return (Criteria) this;
        }

        public Criteria andECoinGreaterThanOrEqualTo(Long value) {
            addCriterion("e_coin >=", value, "eCoin");
            return (Criteria) this;
        }

        public Criteria andECoinLessThan(Long value) {
            addCriterion("e_coin <", value, "eCoin");
            return (Criteria) this;
        }

        public Criteria andECoinLessThanOrEqualTo(Long value) {
            addCriterion("e_coin <=", value, "eCoin");
            return (Criteria) this;
        }

        public Criteria andECoinIn(List<Long> values) {
            addCriterion("e_coin in", values, "eCoin");
            return (Criteria) this;
        }

        public Criteria andECoinNotIn(List<Long> values) {
            addCriterion("e_coin not in", values, "eCoin");
            return (Criteria) this;
        }

        public Criteria andECoinBetween(Long value1, Long value2) {
            addCriterion("e_coin between", value1, value2, "eCoin");
            return (Criteria) this;
        }

        public Criteria andECoinNotBetween(Long value1, Long value2) {
            addCriterion("e_coin not between", value1, value2, "eCoin");
            return (Criteria) this;
        }

        public Criteria andEExchangeIsNull() {
            addCriterion("e_exchange is null");
            return (Criteria) this;
        }

        public Criteria andEExchangeIsNotNull() {
            addCriterion("e_exchange is not null");
            return (Criteria) this;
        }

        public Criteria andEExchangeEqualTo(Double value) {
            addCriterion("e_exchange =", value, "eExchange");
            return (Criteria) this;
        }

        public Criteria andEExchangeNotEqualTo(Double value) {
            addCriterion("e_exchange <>", value, "eExchange");
            return (Criteria) this;
        }

        public Criteria andEExchangeGreaterThan(Double value) {
            addCriterion("e_exchange >", value, "eExchange");
            return (Criteria) this;
        }

        public Criteria andEExchangeGreaterThanOrEqualTo(Double value) {
            addCriterion("e_exchange >=", value, "eExchange");
            return (Criteria) this;
        }

        public Criteria andEExchangeLessThan(Double value) {
            addCriterion("e_exchange <", value, "eExchange");
            return (Criteria) this;
        }

        public Criteria andEExchangeLessThanOrEqualTo(Double value) {
            addCriterion("e_exchange <=", value, "eExchange");
            return (Criteria) this;
        }

        public Criteria andEExchangeIn(List<Double> values) {
            addCriterion("e_exchange in", values, "eExchange");
            return (Criteria) this;
        }

        public Criteria andEExchangeNotIn(List<Double> values) {
            addCriterion("e_exchange not in", values, "eExchange");
            return (Criteria) this;
        }

        public Criteria andEExchangeBetween(Double value1, Double value2) {
            addCriterion("e_exchange between", value1, value2, "eExchange");
            return (Criteria) this;
        }

        public Criteria andEExchangeNotBetween(Double value1, Double value2) {
            addCriterion("e_exchange not between", value1, value2, "eExchange");
            return (Criteria) this;
        }

        public Criteria andRmbEarningIsNull() {
            addCriterion("rmb_earning is null");
            return (Criteria) this;
        }

        public Criteria andRmbEarningIsNotNull() {
            addCriterion("rmb_earning is not null");
            return (Criteria) this;
        }

        public Criteria andRmbEarningEqualTo(Long value) {
            addCriterion("rmb_earning =", value, "rmbEarning");
            return (Criteria) this;
        }

        public Criteria andRmbEarningNotEqualTo(Long value) {
            addCriterion("rmb_earning <>", value, "rmbEarning");
            return (Criteria) this;
        }

        public Criteria andRmbEarningGreaterThan(Long value) {
            addCriterion("rmb_earning >", value, "rmbEarning");
            return (Criteria) this;
        }

        public Criteria andRmbEarningGreaterThanOrEqualTo(Long value) {
            addCriterion("rmb_earning >=", value, "rmbEarning");
            return (Criteria) this;
        }

        public Criteria andRmbEarningLessThan(Long value) {
            addCriterion("rmb_earning <", value, "rmbEarning");
            return (Criteria) this;
        }

        public Criteria andRmbEarningLessThanOrEqualTo(Long value) {
            addCriterion("rmb_earning <=", value, "rmbEarning");
            return (Criteria) this;
        }

        public Criteria andRmbEarningIn(List<Long> values) {
            addCriterion("rmb_earning in", values, "rmbEarning");
            return (Criteria) this;
        }

        public Criteria andRmbEarningNotIn(List<Long> values) {
            addCriterion("rmb_earning not in", values, "rmbEarning");
            return (Criteria) this;
        }

        public Criteria andRmbEarningBetween(Long value1, Long value2) {
            addCriterion("rmb_earning between", value1, value2, "rmbEarning");
            return (Criteria) this;
        }

        public Criteria andRmbEarningNotBetween(Long value1, Long value2) {
            addCriterion("rmb_earning not between", value1, value2, "rmbEarning");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}