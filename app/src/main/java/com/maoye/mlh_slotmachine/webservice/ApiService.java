package com.maoye.mlh_slotmachine.webservice;


import com.maoye.mlh_slotmachine.bean.AddressBean;
import com.maoye.mlh_slotmachine.bean.AdvertBean;
import com.maoye.mlh_slotmachine.bean.BaseResult;
import com.maoye.mlh_slotmachine.bean.BrandGoodsBean;
import com.maoye.mlh_slotmachine.bean.BrandGoodsListBean;
import com.maoye.mlh_slotmachine.bean.DelivetyWayBean;
import com.maoye.mlh_slotmachine.bean.FeeBean;
import com.maoye.mlh_slotmachine.bean.GoodsBean;
import com.maoye.mlh_slotmachine.bean.GoodsDetialsBean;
import com.maoye.mlh_slotmachine.bean.HomeBean;
import com.maoye.mlh_slotmachine.bean.OrderDetialBean;
import com.maoye.mlh_slotmachine.bean.OrderIdBean;
import com.maoye.mlh_slotmachine.bean.PayCodeBean;
import com.maoye.mlh_slotmachine.bean.ProvinceBean;
import com.maoye.mlh_slotmachine.bean.QuickBillTitleBean;
import com.maoye.mlh_slotmachine.bean.QuickOrderBean;
import com.maoye.mlh_slotmachine.bean.QuickOrderDetialsBean;
import com.maoye.mlh_slotmachine.bean.QuickPayWXBean;
import com.maoye.mlh_slotmachine.bean.SapIdBean;
import com.maoye.mlh_slotmachine.bean.SubmitOrderBean;
import com.maoye.mlh_slotmachine.bean.VersionInfoBean;
import com.maoye.mlh_slotmachine.util.DeviceInfoUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;


public interface ApiService {

    @GET(URL.HOME_DATA)
    Observable<BaseResult<HomeBean>> homedata();

    @GET(URL.querySapId)
    Observable<BaseResult<SapIdBean>> querySapId();

    @GET(URL.GOODS_DETIALS)
    Observable<BaseResult<GoodsDetialsBean>> goodsDetials(@Query("id") int id);

    /**
     * 统计广告点击量
     *
     * @param id 广告id
     * @return
     */
    @GET(URL.STATISTIC)
    Observable<BaseResult<GoodsDetialsBean>> statistic(@Query("id") int id);

    @GET(URL.ACCOUNT_LOGIN)
    Observable<BaseResult> accountLogin(@Query("mobile") String mobile, @Query("password") String psw);

    /**
     * 手机号码登录
     *
     * @param mobile
     * @param type   2
     * @return
     */
    @GET(URL.MOBILE_LOGIN)
    Observable<BaseResult> mobileLogin(@Query("mobile") String mobile, @Query("type") int type,@Query("code") String code);

    /**
     * @param id      商品id
     * @param spec_id 规格id
     * @param num     商品数量
     * @return
     */
    @GET(URL.ADD_CART)
    Observable<BaseResult> addCart(@Query("id") int id, @Query("spec_id") int spec_id, @Query("num") int num);

    /**
     * @return
     */
    @GET(URL.CART_LIST)
    Observable<BaseResult<List<GoodsBean>>> cartlist();

    @GET(URL.VERSION_INFO)
    Observable<BaseResult<VersionInfoBean>> versionInfo();

    /**
     * 省
     *
     * @return
     */
    @GET(URL.PROVINCE)
    Observable<BaseResult<List<ProvinceBean>>> province();

    /**
     * 市区
     *
     * @param id 市区id
     * @return
     */
    @GET(URL.PROVINCE)
    Observable<BaseResult<List<ProvinceBean>>> city(@Query("id") int id);

    /**
     * 添加收货地址
     *
     * @param name        姓名
     * @param phone       手机号码
     * @param province_id 省份id
     * @param city_id     市id
     * @param street      具体地址
     * @param area_id     区地址
     * @return
     */
    @GET(URL.ADD_ADDRESS)
    Observable<BaseResult> addAddress(@Query("name") String name, @Query("phone") String phone, @Query("province_id") int province_id, @Query("city_id") int city_id, @Query("area_id") int area_id, @Query("street") String street);

    /**
     * 地址列表
     *
     * @return
     */
    @GET(URL.ADDRESS_LIST)
    Observable<BaseResult<AddressBean>> addressList();

    /**
     * 修改购物车商品数量
     *
     * @param index 商品在购物车中的位置
     * @param num   最终数量
     * @return
     */
    @GET(URL.CHANGE_GOODS_NUM)
    Observable<BaseResult> changeGoodsNum(@Query("index") int index, @Query("num") int num);

    /**
     * 删除购物车
     * type 2
     *
     * @return
     */
    @GET(URL.DELETE_CART_ALL)
    Observable<BaseResult> deleteCartAll(@Query("type") int type);

    /**
     * 删除购物车商品
     *
     * @param indexs 商品位置字符串如   1,2,
     * @return
     */
    @GET(URL.DELETE_CART_ALL)
    Observable<BaseResult> deleteCart(@Query("indexs") String indexs);

    /**
     * @param area_id
     * @param ship_type    0-快递 1-EMS 2-平邮
     * @param product_info 商品id,商品数量集合
     * @return
     */
    @GET(URL.FREIGHT)
    Observable<BaseResult<FeeBean>> freight(@Query("area_id") int area_id, @Query("ship_type") int ship_type, @Query("product_info") String product_info);

    /**
     * 提交订单
     *
     * @param submitOrderBean
     * @return
     */
    @POST(URL.SUBMIT_ORDER)
    Observable<BaseResult<OrderIdBean>> submitOrder(@Body SubmitOrderBean submitOrderBean);

    /**
     * 订单详情
     *
     * @param order_id
     * @return
     */
    @GET(URL.ORDER_DETIALS)
    Observable<BaseResult<OrderDetialBean>> orderDetials(@Query("order_id") int order_id);

    /**
     * 广告轮播
     *
     * @param type 1:登录界面  2：支付成功界面 3.屏保
     * @return
     */
    @GET(URL.ADVERT)
    Observable<BaseResult<List<AdvertBean>>> advert(@Query("type") int type);


    /**
     * 打印机状态
     *
     * @param paper_status 0 打印机正常 1:无纸 2：打印机异常
     * @return
     */
    @GET(URL.PRINTER_STATUS)
    Observable<BaseResult> printer_status(@Query("paper_status") int paper_status);

    /**
     * 设备是否关机
     *
     * @param machine_status 0-关机1开机
     * @return
     */
    @GET(URL.PRINTER_STATUS)
    Observable<BaseResult> deveice_status(@Query("machine_status") int machine_status);

    /**
     * 获取支付二维码
     *
     * @param pay_type 支付类型 1-微信|2-支付宝
     * @param order_id 订单ID
     * @return
     */
    @GET(URL.PAY_CODE)
    Observable<BaseResult<PayCodeBean>> pay_code(@Query("pay_type") int pay_type, @Query("order_id") int order_id);

    /**
     * 扫码支付
     *
     * @param pay_type  授权码以1开头为微信，否则为支付宝
     * @param order_id  订单id
     * @param auth_code 授权码
     * @return
     */
    @GET(URL.SCAN_PAY)
    Observable<BaseResult> scanPay(@Query("pay_type") int pay_type, @Query("order_id") int order_id, @Query("auth_code") String auth_code);


    /**
     * 刷新订单
     *
     * @param order_id
     * @return
     */
    @GET(URL.REFRESH_ORDER)
    Observable<BaseResult<OrderIdBean>> changeOrderNo(@Query("order_id") int order_id);


    @GET(URL.QUICK_ORDER_LIST)
    Observable<BaseResult<ArrayList<QuickOrderBean>>> quickOrderList(@Query("phone") String phone);

    /**
     * 查询商品支持多点配送方式
     *
     * @param product_ids 购买的商品id，多个逗号分隔
     * @return
     */
    @GET(URL.DELIVERY_WAY)
    Observable<BaseResult<List<DelivetyWayBean>>> deliveryWay(@Query("product_ids") String product_ids);


    @GET(URL.QUICK_WXPAY)
    Observable<BaseResult<QuickPayWXBean>> quickWxPay(@Query("saleNo") String saleNo, @Query("money") String money);

    @GET(URL.ALI_PAYCODE)
    Observable<BaseResult<QuickPayWXBean>> quickAliPayCode(@Query("saleNo") String saleNo, @Query("money") String money);

    /**
     * 更新订单状态
     * @param saleNo 流水号
     * @return
     */
    @GET(URL.UPDATE_BILL)
    Observable<BaseResult> updateBill(@Query("saleNo") String saleNo);

    /**
     * 查询是否支付
     * @param payJson  payJson:[{"saleNo":"10012018050299990023","paymodeId":"1091","payAmount":5,"paymodeName":"微商城（支付宝）","billNo":null}    1088是微信
     * @return
     */
    @GET(URL.BILL_QUERY)
    Observable<BaseResult<String>> billQuery(@Query("payJson") String payJson);


    /**
     * 快付订单信息
     * @param saleNo   门店id+ ","+日期（年月日）+","+十位流水号

     * @return
     */
    @GET(URL.QUICK_ORDER_DATA)
    Observable<BaseResult<QuickOrderDetialsBean>> quickOrderData(@Query("saleNo") String saleNo);

    @GET(URL.QUICK_ORDER_DATA)
    Observable<BaseResult<QuickOrderDetialsBean>> quickOrderData2(@Query("saleNo") String saleNo ,@Query("checkReturn") int checkReturn);

    /**
     * 快付获取门店和手机号
     * @param shopNo
     * @return
     */
    @GET(URL.QUICK_BILL_INFO)
    Observable<BaseResult<List<QuickBillTitleBean>>> quickBillInfo(@Query("shopNo") String shopNo);

    @GET(URL.QUICK_MARK_PRINT)
    Observable<BaseResult> quickMarkPrint(@Query("saleNo") String shopNo);

    /**
     * 专柜详情
     * @param id 专柜id
     * @param page 当前请求的页数
     * @param limit 当前请求数据数量
     * @return
     */
    @GET(URL.BRAND_DETIAL)
    Observable<BaseResult<BrandGoodsListBean>> brandDetial(@Query("id") String id , @Query("page") int page , @Query("limit") int limit );

    /**
     * 获取验证码
     * @param mobile
     * @return
     */
   @GET(URL.getCaptcha)
    Observable<BaseResult> getCaptcha(@Query("mobile") String mobile);

    /**
     * 标记打小票
     * @param order_id
     * @return
     */
   @GET(URL.markBillStatus)
    Observable<BaseResult> markBillStatus(@Query("order_id") int order_id);


   @GET(URL.DEFAULT_GOODS)
    Observable<BaseResult<List<GoodsBean>>> defaultGoods();


   @GET(URL.SEACH_GOODS)
    Observable<BaseResult<BrandGoodsBean>> searchGoods(@Query("name")String name);

   @GET(URL.BRAND_GOODS)
    Observable<BaseResult<BrandGoodsBean>> brandGoods(@Query("id")int id);

}
