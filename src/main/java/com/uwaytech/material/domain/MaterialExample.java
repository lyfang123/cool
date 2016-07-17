package com.uwaytech.material.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MaterialExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MaterialExample() {
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

        public Criteria andPersistIdIsNull() {
            addCriterion("persist_id is null");
            return (Criteria) this;
        }

        public Criteria andPersistIdIsNotNull() {
            addCriterion("persist_id is not null");
            return (Criteria) this;
        }

        public Criteria andPersistIdEqualTo(String value) {
            addCriterion("persist_id =", value, "persistId");
            return (Criteria) this;
        }

        public Criteria andPersistIdNotEqualTo(String value) {
            addCriterion("persist_id <>", value, "persistId");
            return (Criteria) this;
        }

        public Criteria andPersistIdGreaterThan(String value) {
            addCriterion("persist_id >", value, "persistId");
            return (Criteria) this;
        }

        public Criteria andPersistIdGreaterThanOrEqualTo(String value) {
            addCriterion("persist_id >=", value, "persistId");
            return (Criteria) this;
        }

        public Criteria andPersistIdLessThan(String value) {
            addCriterion("persist_id <", value, "persistId");
            return (Criteria) this;
        }

        public Criteria andPersistIdLessThanOrEqualTo(String value) {
            addCriterion("persist_id <=", value, "persistId");
            return (Criteria) this;
        }

        public Criteria andPersistIdLike(String value) {
            addCriterion("persist_id like", value, "persistId");
            return (Criteria) this;
        }

        public Criteria andPersistIdNotLike(String value) {
            addCriterion("persist_id not like", value, "persistId");
            return (Criteria) this;
        }

        public Criteria andPersistIdIn(List<String> values) {
            addCriterion("persist_id in", values, "persistId");
            return (Criteria) this;
        }

        public Criteria andPersistIdNotIn(List<String> values) {
            addCriterion("persist_id not in", values, "persistId");
            return (Criteria) this;
        }

        public Criteria andPersistIdBetween(String value1, String value2) {
            addCriterion("persist_id between", value1, value2, "persistId");
            return (Criteria) this;
        }

        public Criteria andPersistIdNotBetween(String value1, String value2) {
            addCriterion("persist_id not between", value1, value2, "persistId");
            return (Criteria) this;
        }

        public Criteria andHashIsNull() {
            addCriterion("hash is null");
            return (Criteria) this;
        }

        public Criteria andHashIsNotNull() {
            addCriterion("hash is not null");
            return (Criteria) this;
        }

        public Criteria andHashEqualTo(String value) {
            addCriterion("hash =", value, "hash");
            return (Criteria) this;
        }

        public Criteria andHashNotEqualTo(String value) {
            addCriterion("hash <>", value, "hash");
            return (Criteria) this;
        }

        public Criteria andHashGreaterThan(String value) {
            addCriterion("hash >", value, "hash");
            return (Criteria) this;
        }

        public Criteria andHashGreaterThanOrEqualTo(String value) {
            addCriterion("hash >=", value, "hash");
            return (Criteria) this;
        }

        public Criteria andHashLessThan(String value) {
            addCriterion("hash <", value, "hash");
            return (Criteria) this;
        }

        public Criteria andHashLessThanOrEqualTo(String value) {
            addCriterion("hash <=", value, "hash");
            return (Criteria) this;
        }

        public Criteria andHashLike(String value) {
            addCriterion("hash like", value, "hash");
            return (Criteria) this;
        }

        public Criteria andHashNotLike(String value) {
            addCriterion("hash not like", value, "hash");
            return (Criteria) this;
        }

        public Criteria andHashIn(List<String> values) {
            addCriterion("hash in", values, "hash");
            return (Criteria) this;
        }

        public Criteria andHashNotIn(List<String> values) {
            addCriterion("hash not in", values, "hash");
            return (Criteria) this;
        }

        public Criteria andHashBetween(String value1, String value2) {
            addCriterion("hash between", value1, value2, "hash");
            return (Criteria) this;
        }

        public Criteria andHashNotBetween(String value1, String value2) {
            addCriterion("hash not between", value1, value2, "hash");
            return (Criteria) this;
        }

        public Criteria andQiniuKeyIsNull() {
            addCriterion("qiniu_key is null");
            return (Criteria) this;
        }

        public Criteria andQiniuKeyIsNotNull() {
            addCriterion("qiniu_key is not null");
            return (Criteria) this;
        }

        public Criteria andQiniuKeyEqualTo(String value) {
            addCriterion("qiniu_key =", value, "qiniuKey");
            return (Criteria) this;
        }

        public Criteria andQiniuKeyNotEqualTo(String value) {
            addCriterion("qiniu_key <>", value, "qiniuKey");
            return (Criteria) this;
        }

        public Criteria andQiniuKeyGreaterThan(String value) {
            addCriterion("qiniu_key >", value, "qiniuKey");
            return (Criteria) this;
        }

        public Criteria andQiniuKeyGreaterThanOrEqualTo(String value) {
            addCriterion("qiniu_key >=", value, "qiniuKey");
            return (Criteria) this;
        }

        public Criteria andQiniuKeyLessThan(String value) {
            addCriterion("qiniu_key <", value, "qiniuKey");
            return (Criteria) this;
        }

        public Criteria andQiniuKeyLessThanOrEqualTo(String value) {
            addCriterion("qiniu_key <=", value, "qiniuKey");
            return (Criteria) this;
        }

        public Criteria andQiniuKeyLike(String value) {
            addCriterion("qiniu_key like", value, "qiniuKey");
            return (Criteria) this;
        }

        public Criteria andQiniuKeyNotLike(String value) {
            addCriterion("qiniu_key not like", value, "qiniuKey");
            return (Criteria) this;
        }

        public Criteria andQiniuKeyIn(List<String> values) {
            addCriterion("qiniu_key in", values, "qiniuKey");
            return (Criteria) this;
        }

        public Criteria andQiniuKeyNotIn(List<String> values) {
            addCriterion("qiniu_key not in", values, "qiniuKey");
            return (Criteria) this;
        }

        public Criteria andQiniuKeyBetween(String value1, String value2) {
            addCriterion("qiniu_key between", value1, value2, "qiniuKey");
            return (Criteria) this;
        }

        public Criteria andQiniuKeyNotBetween(String value1, String value2) {
            addCriterion("qiniu_key not between", value1, value2, "qiniuKey");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andMediaTypeIsNull() {
            addCriterion("media_type is null");
            return (Criteria) this;
        }

        public Criteria andMediaTypeIsNotNull() {
            addCriterion("media_type is not null");
            return (Criteria) this;
        }

        public Criteria andMediaTypeEqualTo(String value) {
            addCriterion("media_type =", value, "mediaType");
            return (Criteria) this;
        }

        public Criteria andMediaTypeNotEqualTo(String value) {
            addCriterion("media_type <>", value, "mediaType");
            return (Criteria) this;
        }

        public Criteria andMediaTypeGreaterThan(String value) {
            addCriterion("media_type >", value, "mediaType");
            return (Criteria) this;
        }

        public Criteria andMediaTypeGreaterThanOrEqualTo(String value) {
            addCriterion("media_type >=", value, "mediaType");
            return (Criteria) this;
        }

        public Criteria andMediaTypeLessThan(String value) {
            addCriterion("media_type <", value, "mediaType");
            return (Criteria) this;
        }

        public Criteria andMediaTypeLessThanOrEqualTo(String value) {
            addCriterion("media_type <=", value, "mediaType");
            return (Criteria) this;
        }

        public Criteria andMediaTypeLike(String value) {
            addCriterion("media_type like", value, "mediaType");
            return (Criteria) this;
        }

        public Criteria andMediaTypeNotLike(String value) {
            addCriterion("media_type not like", value, "mediaType");
            return (Criteria) this;
        }

        public Criteria andMediaTypeIn(List<String> values) {
            addCriterion("media_type in", values, "mediaType");
            return (Criteria) this;
        }

        public Criteria andMediaTypeNotIn(List<String> values) {
            addCriterion("media_type not in", values, "mediaType");
            return (Criteria) this;
        }

        public Criteria andMediaTypeBetween(String value1, String value2) {
            addCriterion("media_type between", value1, value2, "mediaType");
            return (Criteria) this;
        }

        public Criteria andMediaTypeNotBetween(String value1, String value2) {
            addCriterion("media_type not between", value1, value2, "mediaType");
            return (Criteria) this;
        }

        public Criteria andSizeIsNull() {
            addCriterion("size is null");
            return (Criteria) this;
        }

        public Criteria andSizeIsNotNull() {
            addCriterion("size is not null");
            return (Criteria) this;
        }

        public Criteria andSizeEqualTo(String value) {
            addCriterion("size =", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotEqualTo(String value) {
            addCriterion("size <>", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeGreaterThan(String value) {
            addCriterion("size >", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeGreaterThanOrEqualTo(String value) {
            addCriterion("size >=", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeLessThan(String value) {
            addCriterion("size <", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeLessThanOrEqualTo(String value) {
            addCriterion("size <=", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeLike(String value) {
            addCriterion("size like", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotLike(String value) {
            addCriterion("size not like", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeIn(List<String> values) {
            addCriterion("size in", values, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotIn(List<String> values) {
            addCriterion("size not in", values, "size");
            return (Criteria) this;
        }

        public Criteria andSizeBetween(String value1, String value2) {
            addCriterion("size between", value1, value2, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotBetween(String value1, String value2) {
            addCriterion("size not between", value1, value2, "size");
            return (Criteria) this;
        }

        public Criteria andCusCateIdIsNull() {
            addCriterion("cus_cate_id is null");
            return (Criteria) this;
        }

        public Criteria andCusCateIdIsNotNull() {
            addCriterion("cus_cate_id is not null");
            return (Criteria) this;
        }

        public Criteria andCusCateIdEqualTo(Long value) {
            addCriterion("cus_cate_id =", value, "cusCateId");
            return (Criteria) this;
        }

        public Criteria andCusCateIdNotEqualTo(Long value) {
            addCriterion("cus_cate_id <>", value, "cusCateId");
            return (Criteria) this;
        }

        public Criteria andCusCateIdGreaterThan(Long value) {
            addCriterion("cus_cate_id >", value, "cusCateId");
            return (Criteria) this;
        }

        public Criteria andCusCateIdGreaterThanOrEqualTo(Long value) {
            addCriterion("cus_cate_id >=", value, "cusCateId");
            return (Criteria) this;
        }

        public Criteria andCusCateIdLessThan(Long value) {
            addCriterion("cus_cate_id <", value, "cusCateId");
            return (Criteria) this;
        }

        public Criteria andCusCateIdLessThanOrEqualTo(Long value) {
            addCriterion("cus_cate_id <=", value, "cusCateId");
            return (Criteria) this;
        }

        public Criteria andCusCateIdIn(List<Long> values) {
            addCriterion("cus_cate_id in", values, "cusCateId");
            return (Criteria) this;
        }

        public Criteria andCusCateIdNotIn(List<Long> values) {
            addCriterion("cus_cate_id not in", values, "cusCateId");
            return (Criteria) this;
        }

        public Criteria andCusCateIdBetween(Long value1, Long value2) {
            addCriterion("cus_cate_id between", value1, value2, "cusCateId");
            return (Criteria) this;
        }

        public Criteria andCusCateIdNotBetween(Long value1, Long value2) {
            addCriterion("cus_cate_id not between", value1, value2, "cusCateId");
            return (Criteria) this;
        }

        public Criteria andLengthIsNull() {
            addCriterion("length is null");
            return (Criteria) this;
        }

        public Criteria andLengthIsNotNull() {
            addCriterion("length is not null");
            return (Criteria) this;
        }

        public Criteria andLengthEqualTo(Integer value) {
            addCriterion("length =", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthNotEqualTo(Integer value) {
            addCriterion("length <>", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthGreaterThan(Integer value) {
            addCriterion("length >", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthGreaterThanOrEqualTo(Integer value) {
            addCriterion("length >=", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthLessThan(Integer value) {
            addCriterion("length <", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthLessThanOrEqualTo(Integer value) {
            addCriterion("length <=", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthIn(List<Integer> values) {
            addCriterion("length in", values, "length");
            return (Criteria) this;
        }

        public Criteria andLengthNotIn(List<Integer> values) {
            addCriterion("length not in", values, "length");
            return (Criteria) this;
        }

        public Criteria andLengthBetween(Integer value1, Integer value2) {
            addCriterion("length between", value1, value2, "length");
            return (Criteria) this;
        }

        public Criteria andLengthNotBetween(Integer value1, Integer value2) {
            addCriterion("length not between", value1, value2, "length");
            return (Criteria) this;
        }

        public Criteria andUrlIsNull() {
            addCriterion("url is null");
            return (Criteria) this;
        }

        public Criteria andUrlIsNotNull() {
            addCriterion("url is not null");
            return (Criteria) this;
        }

        public Criteria andUrlEqualTo(String value) {
            addCriterion("url =", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotEqualTo(String value) {
            addCriterion("url <>", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThan(String value) {
            addCriterion("url >", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThanOrEqualTo(String value) {
            addCriterion("url >=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThan(String value) {
            addCriterion("url <", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThanOrEqualTo(String value) {
            addCriterion("url <=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLike(String value) {
            addCriterion("url like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotLike(String value) {
            addCriterion("url not like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlIn(List<String> values) {
            addCriterion("url in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotIn(List<String> values) {
            addCriterion("url not in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlBetween(String value1, String value2) {
            addCriterion("url between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotBetween(String value1, String value2) {
            addCriterion("url not between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andNormUrlIsNull() {
            addCriterion("norm_url is null");
            return (Criteria) this;
        }

        public Criteria andNormUrlIsNotNull() {
            addCriterion("norm_url is not null");
            return (Criteria) this;
        }

        public Criteria andNormUrlEqualTo(String value) {
            addCriterion("norm_url =", value, "normUrl");
            return (Criteria) this;
        }

        public Criteria andNormUrlNotEqualTo(String value) {
            addCriterion("norm_url <>", value, "normUrl");
            return (Criteria) this;
        }

        public Criteria andNormUrlGreaterThan(String value) {
            addCriterion("norm_url >", value, "normUrl");
            return (Criteria) this;
        }

        public Criteria andNormUrlGreaterThanOrEqualTo(String value) {
            addCriterion("norm_url >=", value, "normUrl");
            return (Criteria) this;
        }

        public Criteria andNormUrlLessThan(String value) {
            addCriterion("norm_url <", value, "normUrl");
            return (Criteria) this;
        }

        public Criteria andNormUrlLessThanOrEqualTo(String value) {
            addCriterion("norm_url <=", value, "normUrl");
            return (Criteria) this;
        }

        public Criteria andNormUrlLike(String value) {
            addCriterion("norm_url like", value, "normUrl");
            return (Criteria) this;
        }

        public Criteria andNormUrlNotLike(String value) {
            addCriterion("norm_url not like", value, "normUrl");
            return (Criteria) this;
        }

        public Criteria andNormUrlIn(List<String> values) {
            addCriterion("norm_url in", values, "normUrl");
            return (Criteria) this;
        }

        public Criteria andNormUrlNotIn(List<String> values) {
            addCriterion("norm_url not in", values, "normUrl");
            return (Criteria) this;
        }

        public Criteria andNormUrlBetween(String value1, String value2) {
            addCriterion("norm_url between", value1, value2, "normUrl");
            return (Criteria) this;
        }

        public Criteria andNormUrlNotBetween(String value1, String value2) {
            addCriterion("norm_url not between", value1, value2, "normUrl");
            return (Criteria) this;
        }

        public Criteria andHighUrlIsNull() {
            addCriterion("high_url is null");
            return (Criteria) this;
        }

        public Criteria andHighUrlIsNotNull() {
            addCriterion("high_url is not null");
            return (Criteria) this;
        }

        public Criteria andHighUrlEqualTo(String value) {
            addCriterion("high_url =", value, "highUrl");
            return (Criteria) this;
        }

        public Criteria andHighUrlNotEqualTo(String value) {
            addCriterion("high_url <>", value, "highUrl");
            return (Criteria) this;
        }

        public Criteria andHighUrlGreaterThan(String value) {
            addCriterion("high_url >", value, "highUrl");
            return (Criteria) this;
        }

        public Criteria andHighUrlGreaterThanOrEqualTo(String value) {
            addCriterion("high_url >=", value, "highUrl");
            return (Criteria) this;
        }

        public Criteria andHighUrlLessThan(String value) {
            addCriterion("high_url <", value, "highUrl");
            return (Criteria) this;
        }

        public Criteria andHighUrlLessThanOrEqualTo(String value) {
            addCriterion("high_url <=", value, "highUrl");
            return (Criteria) this;
        }

        public Criteria andHighUrlLike(String value) {
            addCriterion("high_url like", value, "highUrl");
            return (Criteria) this;
        }

        public Criteria andHighUrlNotLike(String value) {
            addCriterion("high_url not like", value, "highUrl");
            return (Criteria) this;
        }

        public Criteria andHighUrlIn(List<String> values) {
            addCriterion("high_url in", values, "highUrl");
            return (Criteria) this;
        }

        public Criteria andHighUrlNotIn(List<String> values) {
            addCriterion("high_url not in", values, "highUrl");
            return (Criteria) this;
        }

        public Criteria andHighUrlBetween(String value1, String value2) {
            addCriterion("high_url between", value1, value2, "highUrl");
            return (Criteria) this;
        }

        public Criteria andHighUrlNotBetween(String value1, String value2) {
            addCriterion("high_url not between", value1, value2, "highUrl");
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

        public Criteria andQiniuStatusIsNull() {
            addCriterion("qiniu_status is null");
            return (Criteria) this;
        }

        public Criteria andQiniuStatusIsNotNull() {
            addCriterion("qiniu_status is not null");
            return (Criteria) this;
        }

        public Criteria andQiniuStatusEqualTo(Integer value) {
            addCriterion("qiniu_status =", value, "qiniuStatus");
            return (Criteria) this;
        }

        public Criteria andQiniuStatusNotEqualTo(Integer value) {
            addCriterion("qiniu_status <>", value, "qiniuStatus");
            return (Criteria) this;
        }

        public Criteria andQiniuStatusGreaterThan(Integer value) {
            addCriterion("qiniu_status >", value, "qiniuStatus");
            return (Criteria) this;
        }

        public Criteria andQiniuStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("qiniu_status >=", value, "qiniuStatus");
            return (Criteria) this;
        }

        public Criteria andQiniuStatusLessThan(Integer value) {
            addCriterion("qiniu_status <", value, "qiniuStatus");
            return (Criteria) this;
        }

        public Criteria andQiniuStatusLessThanOrEqualTo(Integer value) {
            addCriterion("qiniu_status <=", value, "qiniuStatus");
            return (Criteria) this;
        }

        public Criteria andQiniuStatusIn(List<Integer> values) {
            addCriterion("qiniu_status in", values, "qiniuStatus");
            return (Criteria) this;
        }

        public Criteria andQiniuStatusNotIn(List<Integer> values) {
            addCriterion("qiniu_status not in", values, "qiniuStatus");
            return (Criteria) this;
        }

        public Criteria andQiniuStatusBetween(Integer value1, Integer value2) {
            addCriterion("qiniu_status between", value1, value2, "qiniuStatus");
            return (Criteria) this;
        }

        public Criteria andQiniuStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("qiniu_status not between", value1, value2, "qiniuStatus");
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