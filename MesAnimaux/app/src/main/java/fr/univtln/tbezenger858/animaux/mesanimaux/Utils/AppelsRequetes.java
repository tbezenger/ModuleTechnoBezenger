package fr.univtln.tbezenger858.animaux.mesanimaux.Utils;


/**
 * Created by Screetts on 19/05/2016.
 */
// Functions to get objects from server :
public class AppelsRequetes {
//
//    public static List<Oiseaux> getAllOiseaux() throws ExecutionException, InterruptedException, IOException, JSONException {
//        String mPortalsJson = RequetesRest.get_All("portals");
//        return new JsonDecoder<CPortal>().DecoderList(mPortalsJson, CPortal.class);
//    }
//
//    public static List<CLink> getAllLinks() throws ExecutionException, InterruptedException, IOException, JSONException {
//        String mLinksJson = RequetesRest.get_All("links");
//        return new JsonDecoder<CLink>().DecoderList(mLinksJson, CLink.class);
//    }
//
//    public static List<CField> getAllFields() throws ExecutionException, InterruptedException, IOException, JSONException {
//        String mFieldsJson = RequetesRest.get_All("fields");
//        return new JsonDecoder<CField>().DecoderList(mFieldsJson, CField.class);
//    }
//
//    public static List<CPlayer> getAllConnectedPlayers() throws ExecutionException, InterruptedException, IOException, JSONException {
//        String mPlayersJson = RequetesRest.get_All("players");
//        return new JsonDecoder<CPlayer>().DecoderList(mPlayersJson, CPlayer.class);
//    }
//
//    public static List<CMapItem> getAllMapItems() throws ExecutionException, InterruptedException, IOException, JSONException {
//        String mItemsJson = RequetesRest.get_All("mapitems");
//        return new JsonDecoder<CMapItem>().DecoderList(mItemsJson, CMapItem.class);
//    }
//
//    public static void addToInventory(AItem pItem) throws ExecutionException, InterruptedException, IOException, JSONException {
//        boolean found = false;
//        for(CInventory mInventory : new JsonDecoder<CInventory>().DecoderList(RequetesRest.get_By(CProperties.PLAYER, CProperties.player.getId(), CProperties.INVENTORY), CInventory.class))
//            if (mInventory.getItem().getId() == pItem.getId()) {
//                mInventory.setQuantity(mInventory.getQuantity() + 1);
//                Log.i("INSERT", mInventory.toString());
//                RequetesRest.put(mInventory, CProperties.INVENTORY);
//                found = true;
//                break;
//            }
//
//        if(! found) {
//            CInventory mInventory = new CInventory();
//            mInventory.setItem(pItem);
//            mInventory.setPlayer(CProperties.player);
//            mInventory.setQuantity(1);
//            Log.i("INSERT", mInventory.toString());
//            RequetesRest.post(mInventory, CProperties.INVENTORY);
//        }
//    }
//
//    public static boolean hasPlayerPortalKey(CPortal pPortal){
//        boolean mHasPlayerPortalKey = false;
////        try {
////            String mJsonInventory = RequetesRest.get_By(CProperties.INVENTORY, CProperties.player.getId(), CProperties.PLAYER);
////            for(CInventory mInventory : new JsonDecoder<CInventory>().DecoderList(mJsonInventory, CInventory.class))
////                if(mInventory.getItem().getId() == pPortal.getKey().getId())
////                    mHasPlayerPortalKey = true;
////        } catch (ExecutionException e) {
////            e.printStackTrace();
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        } catch (JSONException e) {
////            e.printStackTrace();
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
//        return mHasPlayerPortalKey;
//    }

}
