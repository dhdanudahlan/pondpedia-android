package com.pondpedia.android.pondpedia.data.remote

/*
@OptIn(ExperimentalPagingApi::class)
class ChatRemoteMediator(
    private val chatDao: AiChatDao,
    private val chatDb: PondPediaDatabase,
    private val chatApi: AiChatApi
): RemoteMediator<Int, ChatResponseEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ChatResponseEntity>
    ): MediatorResult {
        return try {
            val loadKey = when(loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        1
                    } else {
                        (lastItem.chatId / state.config.pageSize) + 1
                    }
                }
            }

            val chats = chatApi.getChat(
                page = loadKey,
                pageCount = state.config.pageSize
            )

            */
/*chatDb.withTransaction {
                if (loadType = LoadType.REFRESH) {
                    chatDb.chatDao.clearAll()
                }
                val chatResponseEntity = chats.map { it.toChatResponseEntity() }
                chatDb.chatDao.upsertAll(chatResponseEntity)
            }*//*


            val chatResponseEntity = chats.map { it.toChatResponseEntity() }
            if (loadType == LoadType.REFRESH) {
                chatDb.chatDao.refreshAll(chatResponseEntity)
            } else {
                chatDb.chatDao.upsertAll(chatResponseEntity)
            }

            MediatorResult.Success(
                endOfPaginationReached = chats.isEmpty()
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}*/
