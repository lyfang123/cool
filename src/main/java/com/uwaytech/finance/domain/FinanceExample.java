package com.uwaytech.finance.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FinanceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FinanceExample() {
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

        public Criteria andGroupingIsNull() {
            addCriterion("grouping is null");
            return (Criteria) this;
        }

        public Criteria andGroupingIsNotNull() {
            addCriterion("grouping is not null");
            return (Criteria) this;
        }

        public Criteria andGroupingEqualTo(Long value) {
            addCriterion("grouping =", value, "grouping");
            return (Criteria) this;
        }

        public Criteria andGroupingNotEqualTo(Long value) {
            addCriterion("grouping <>", value, "grouping");
            return (Criteria) this;
        }

        public Criteria andGroupingGreaterThan(Long value) {
            addCriterion("grouping >", value, "grouping");
            return (Criteria) this;
        }

        public Criteria andGroupingGreaterThanOrEqualTo(Long value) {
            addCriterion("grouping >=", value, "grouping");
            return (Criteria) this;
        }

        public Criteria andGroupingLessThan(Long value) {
            addCriterion("grouping <", value, "grouping");
            return (Criteria) this;
        }

        public Criteria andGroupingLessThanOrEqualTo(Long value) {
            addCriterion("grouping <=", value, "grouping");
            return (Criteria) this;
        }

        public Criteria andGroupingIn(List<Long> values) {
            addCriterion("grouping in", values, "grouping");
            return (Criteria) this;
        }

        public Criteria andGroupingNotIn(List<Long> values) {
            addCriterion("grouping not in", values, "grouping");
            return (Criteria) this;
        }

        public Criteria andGroupingBetween(Long value1, Long value2) {
            addCriterion("grouping between", value1, value2, "grouping");
            return (Criteria) this;
        }

        public Criteria andGroupingNotBetween(Long value1, Long value2) {
            addCriterion("grouping not between", value1, value2, "grouping");
            return (Criteria) this;
        }

        public Criteria andEBalanceIsNull() {
            addCriterion("e_balance is null");
            return (Criteria) this;
        }

        public Criteria andEBalanceIsNotNull() {
            addCriterion("e_balance is not null");
            return (Criteria) this;
        }

        public Criteria andEBalanceEqualTo(Long value) {
            addCriterion("e_balance =", value, "eBalance");
            return (Criteria) this;
        }

        public Criteria andEBalanceNotEqualTo(Long value) {
            addCriterion("e_balance <>", value, "eBalance");
            return (Criteria) this;
        }

        public Criteria andEBalanceGreaterThan(Long value) {
            addCriterion("e_balance >", value, "eBalance");
            return (Criteria) this;
        }

        public Criteria andEBalanceGreaterThanOrEqualTo(Long value) {
            addCriterion("e_balance >=", value, "eBalance");
            return (Criteria) this;
        }

        public Criteria andEBalanceLessThan(Long value) {
            addCriterion("e_balance <", value, "eBalance");
            return (Criteria) this;
        }

        public Criteria andEBalanceLessThanOrEqualTo(Long value) {
            addCriterion("e_balance <=", value, "eBalance");
            return (Criteria) this;
        }

        public Criteria andEBalanceIn(List<Long> values) {
            addCriterion("e_balance in", values, "eBalance");
            return (Criteria) this;
        }

        public Criteria andEBalanceNotIn(List<Long> values) {
            addCriterion("e_balance not in", values, "eBalance");
            return (Criteria) this;
        }

        public Criteria andEBalanceBetween(Long value1, Long value2) {
            addCriterion("e_balance between", value1, value2, "eBalance");
            return (Criteria) this;
        }

        public Criteria andEBalanceNotBetween(Long value1, Long value2) {
            addCriterion("e_balance not between", value1, value2, "eBalance");
            return (Criteria) this;
        }

        public Criteria andRmbAmountsIsNull() {
            addCriterion("rmb_amounts is null");
            return (Criteria) this;
        }

        public Criteria andRmbAmountsIsNotNull() {
            addCriterion("rmb_amounts is not null");
            return (Criteria) this;
        }

        public Criteria andRmbAmountsEqualTo(Long value) {
            addCriterion("rmb_amounts =", value, "rmbAmounts");
            return (Criteria) this;
        }

        public Criteria andRmbAmountsNotEqualTo(Long value) {
            addCriterion("rmb_amounts <>", value, "rmbAmounts");
            return (Criteria) this;
        }

        public Criteria andRmbAmountsGreaterThan(Long value) {
            addCriterion("rmb_amounts >", value, "rmbAmounts");
            return (Criteria) this;
        }

        public Criteria andRmbAmountsGreaterThanOrEqualTo(Long value) {
            addCriterion("rmb_amounts >=", value, "rmbAmounts");
            return (Criteria) this;
        }

        public Criteria andRmbAmountsLessThan(Long value) {
            addCriterion("rmb_amounts <", value, "rmbAmounts");
            return (Criteria) this;
        }

        public Criteria andRmbAmountsLessThanOrEqualTo(Long value) {
            addCriterion("rmb_amounts <=", value, "rmbAmounts");
            return (Criteria) this;
        }

        public Criteria andRmbAmountsIn(List<Long> values) {
            addCriterion("rmb_amounts in", values, "rmbAmounts");
            return (Criteria) this;
        }

        public Criteria andRmbAmountsNotIn(List<Long> values) {
            addCriterion("rmb_amounts not in", values, "rmbAmounts");
            return (Criteria) this;
        }

        public Criteria andRmbAmountsBetween(Long value1, Long value2) {
            addCriterion("rmb_amounts between", value1, value2, "rmbAmounts");
            return (Criteria) this;
        }

        public Criteria andRmbAmountsNotBetween(Long value1, Long value2) {
            addCriterion("rmb_amounts not between", value1, value2, "rmbAmounts");
            return (Criteria) this;
        }

        public Criteria andEAmountsIsNull() {
            addCriterion("e_amounts is null");
            return (Criteria) this;
        }

        public Criteria andEAmountsIsNotNull() {
            addCriterion("e_amounts is not null");
            return (Criteria) this;
        }

        public Criteria andEAmountsEqualTo(Long value) {
            addCriterion("e_amounts =", value, "eAmounts");
            return (Criteria) this;
        }

        public Criteria andEAmountsNotEqualTo(Long value) {
            addCriterion("e_amounts <>", value, "eAmounts");
            return (Criteria) this;
        }

        public Criteria andEAmountsGreaterThan(Long value) {
            addCriterion("e_amounts >", value, "eAmounts");
            return (Criteria) this;
        }

        public Criteria andEAmountsGreaterThanOrEqualTo(Long value) {
            addCriterion("e_amounts >=", value, "eAmounts");
            return (Criteria) this;
        }

        public Criteria andEAmountsLessThan(Long value) {
            addCriterion("e_amounts <", value, "eAmounts");
            return (Criteria) this;
        }

        public Criteria andEAmountsLessThanOrEqualTo(Long value) {
            addCriterion("e_amounts <=", value, "eAmounts");
            return (Criteria) this;
        }

        public Criteria andEAmountsIn(List<Long> values) {
            addCriterion("e_amounts in", values, "eAmounts");
            return (Criteria) this;
        }

        public Criteria andEAmountsNotIn(List<Long> values) {
            addCriterion("e_amounts not in", values, "eAmounts");
            return (Criteria) this;
        }

        public Criteria andEAmountsBetween(Long value1, Long value2) {
            addCriterion("e_amounts between", value1, value2, "eAmounts");
            return (Criteria) this;
        }

        public Criteria andEAmountsNotBetween(Long value1, Long value2) {
            addCriterion("e_amounts not between", value1, value2, "eAmounts");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Byte value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Byte value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Byte value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Byte value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Byte value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Byte> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Byte> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Byte value1, Byte value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
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