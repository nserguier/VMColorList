package com.training.nicklos.vmcolorlist

import java.util.concurrent.Executor

/**
 * Global executor pools for the whole application.
 *
 * Grouping tasks like this avoids the effects of task starvation (e.g. disk reads don't wait behind
 * webservice requests).
 */
class AppExecutors(val diskIO: Executor,
                   val networkIO: Executor,
                   val mainThread: Executor)