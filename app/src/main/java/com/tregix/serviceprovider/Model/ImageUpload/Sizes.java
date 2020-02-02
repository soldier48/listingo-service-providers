
package com.tregix.serviceprovider.Model.ImageUpload;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sizes {

    @SerializedName("thumbnail")
    @Expose
    private Thumbnail thumbnail;
    @SerializedName("medium")
    @Expose
    private Medium medium;
    @SerializedName("medium_large")
    @Expose
    private MediumLarge mediumLarge;
    @SerializedName("large")
    @Expose
    private Large large;
    @SerializedName("listingo_user_dashboard_banner")
    @Expose
    private ListingoUserDashboardBanner listingoUserDashboardBanner;
    @SerializedName("listingo_blog_large")
    @Expose
    private ListingoBlogLarge listingoBlogLarge;
    @SerializedName("listingo_related_events")
    @Expose
    private ListingoRelatedEvents listingoRelatedEvents;
    @SerializedName("listingo_blog_grid")
    @Expose
    private ListingoBlogGrid listingoBlogGrid;
    @SerializedName("listingo_user_banner_profile")
    @Expose
    private ListingoUserBannerProfile listingoUserBannerProfile;
    @SerializedName("listingo_user_award_image")
    @Expose
    private ListingoUserAwardImage listingoUserAwardImage;
    @SerializedName("listingo_user_profile")
    @Expose
    private ListingoUserProfile listingoUserProfile;
    @SerializedName("woocommerce_thumbnail")
    @Expose
    private WoocommerceThumbnail woocommerceThumbnail;
    @SerializedName("woocommerce_single")
    @Expose
    private WoocommerceSingle woocommerceSingle;
    @SerializedName("woocommerce_gallery_thumbnail")
    @Expose
    private WoocommerceGalleryThumbnail woocommerceGalleryThumbnail;
    @SerializedName("shop_catalog")
    @Expose
    private ShopCatalog shopCatalog;
    @SerializedName("shop_single")
    @Expose
    private ShopSingle shopSingle;
    @SerializedName("shop_thumbnail")
    @Expose
    private ShopThumbnail shopThumbnail;
    @SerializedName("full")
    @Expose
    private Full full;

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Medium getMedium() {
        return medium;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    public MediumLarge getMediumLarge() {
        return mediumLarge;
    }

    public void setMediumLarge(MediumLarge mediumLarge) {
        this.mediumLarge = mediumLarge;
    }

    public Large getLarge() {
        return large;
    }

    public void setLarge(Large large) {
        this.large = large;
    }

    public ListingoUserDashboardBanner getListingoUserDashboardBanner() {
        return listingoUserDashboardBanner;
    }

    public void setListingoUserDashboardBanner(ListingoUserDashboardBanner listingoUserDashboardBanner) {
        this.listingoUserDashboardBanner = listingoUserDashboardBanner;
    }

    public ListingoBlogLarge getListingoBlogLarge() {
        return listingoBlogLarge;
    }

    public void setListingoBlogLarge(ListingoBlogLarge listingoBlogLarge) {
        this.listingoBlogLarge = listingoBlogLarge;
    }

    public ListingoRelatedEvents getListingoRelatedEvents() {
        return listingoRelatedEvents;
    }

    public void setListingoRelatedEvents(ListingoRelatedEvents listingoRelatedEvents) {
        this.listingoRelatedEvents = listingoRelatedEvents;
    }

    public ListingoBlogGrid getListingoBlogGrid() {
        return listingoBlogGrid;
    }

    public void setListingoBlogGrid(ListingoBlogGrid listingoBlogGrid) {
        this.listingoBlogGrid = listingoBlogGrid;
    }

    public ListingoUserBannerProfile getListingoUserBannerProfile() {
        return listingoUserBannerProfile;
    }

    public void setListingoUserBannerProfile(ListingoUserBannerProfile listingoUserBannerProfile) {
        this.listingoUserBannerProfile = listingoUserBannerProfile;
    }

    public ListingoUserAwardImage getListingoUserAwardImage() {
        return listingoUserAwardImage;
    }

    public void setListingoUserAwardImage(ListingoUserAwardImage listingoUserAwardImage) {
        this.listingoUserAwardImage = listingoUserAwardImage;
    }

    public ListingoUserProfile getListingoUserProfile() {
        return listingoUserProfile;
    }

    public void setListingoUserProfile(ListingoUserProfile listingoUserProfile) {
        this.listingoUserProfile = listingoUserProfile;
    }

    public WoocommerceThumbnail getWoocommerceThumbnail() {
        return woocommerceThumbnail;
    }

    public void setWoocommerceThumbnail(WoocommerceThumbnail woocommerceThumbnail) {
        this.woocommerceThumbnail = woocommerceThumbnail;
    }

    public WoocommerceSingle getWoocommerceSingle() {
        return woocommerceSingle;
    }

    public void setWoocommerceSingle(WoocommerceSingle woocommerceSingle) {
        this.woocommerceSingle = woocommerceSingle;
    }

    public WoocommerceGalleryThumbnail getWoocommerceGalleryThumbnail() {
        return woocommerceGalleryThumbnail;
    }

    public void setWoocommerceGalleryThumbnail(WoocommerceGalleryThumbnail woocommerceGalleryThumbnail) {
        this.woocommerceGalleryThumbnail = woocommerceGalleryThumbnail;
    }

    public ShopCatalog getShopCatalog() {
        return shopCatalog;
    }

    public void setShopCatalog(ShopCatalog shopCatalog) {
        this.shopCatalog = shopCatalog;
    }

    public ShopSingle getShopSingle() {
        return shopSingle;
    }

    public void setShopSingle(ShopSingle shopSingle) {
        this.shopSingle = shopSingle;
    }

    public ShopThumbnail getShopThumbnail() {
        return shopThumbnail;
    }

    public void setShopThumbnail(ShopThumbnail shopThumbnail) {
        this.shopThumbnail = shopThumbnail;
    }

    public Full getFull() {
        return full;
    }

    public void setFull(Full full) {
        this.full = full;
    }

}
